# Lira to Euro Converter

A simple Android app that converts Turkish Lira to Euro.

## Features

- Clean and intuitive user interface
- Real-time currency conversion
- Simple calculator-style input
- Optimized for fast performance on Android devices

## Hardcoded Values

The app currently uses a hardcoded exchange rate:

```kotlin
val euro = lira / 41.6  // Exchange rate: 1 Euro = 41.6 Turkish Lira
```

This exchange rate (41.6 TRY to 1 EUR) is fixed in the code. In a production app, this would ideally be replaced with:
- A call to a currency exchange rate API
- Regular updates to ensure accuracy
- Possibly offline caching of recent rates

## Building and Installation

### Prerequisites
- Android Studio
- Android SDK with minimum API level 31 (Android 12)

### Installation
1. Clone this repository
2. Open the project in Android Studio
3. Build the app using Gradle: `./gradlew assembleDebug`
4. Install on your device: `adb install app/build/outputs/apk/debug/app-debug.apk`

## License

This project is open source and available for personal and educational use.

## Future Improvements

- Add real-time exchange rate updates
- Support for additional currencies
- Dark mode support
- Historical rate tracking
