#!/bin/bash

# Create a keystore directory if it doesn't exist
mkdir -p keystore

# Check if keystore already exists
if [ ! -f keystore/release.keystore ]; then
    echo "Creating new keystore..."
    # Generate a new keystore
    keytool -genkey -v -keystore keystore/release.keystore -alias lira_euro -keyalg RSA -keysize 2048 -validity 10000
else
    echo "Using existing keystore"
fi

# Sign the APK
echo "Signing the APK..."
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore keystore/release.keystore app/build/outputs/apk/release/app-release-unsigned.apk lira_euro

# Verify the signed APK
echo "Verifying the signed APK..."
jarsigner -verify -verbose -certs app/build/outputs/apk/release/app-release-unsigned.apk

# Align the APK (optional but recommended)
echo "Aligning the APK..."

# Try to find zipalign in common locations
ZIPALIGN_CMD="zipalign"

# Check if zipalign is in PATH
if ! command -v $ZIPALIGN_CMD &> /dev/null; then
    # Try to find it in Android SDK
    if [ -d "$HOME/Library/Android/sdk/build-tools" ]; then
        # Get the latest version directory
        LATEST_BUILD_TOOLS=$(ls -1 "$HOME/Library/Android/sdk/build-tools" | sort -V | tail -n 1)
        if [ -n "$LATEST_BUILD_TOOLS" ] && [ -f "$HOME/Library/Android/sdk/build-tools/$LATEST_BUILD_TOOLS/zipalign" ]; then
            ZIPALIGN_CMD="$HOME/Library/Android/sdk/build-tools/$LATEST_BUILD_TOOLS/zipalign"
            echo "Found zipalign at: $ZIPALIGN_CMD"
        fi
    fi
fi

# Check if we found zipalign
if command -v $ZIPALIGN_CMD &> /dev/null; then
    $ZIPALIGN_CMD -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app-release.apk
else
    echo "zipalign not found. Skipping alignment step."
    # Just copy the signed APK
    cp app/build/outputs/apk/release/app-release-unsigned.apk app-release.apk
    echo "Copied signed APK to app-release.apk"
fi

echo "Done! The signed APK is at: app-release.apk"
echo "You can install it with: adb -s RF8X70DML9K install -r app-release.apk"
