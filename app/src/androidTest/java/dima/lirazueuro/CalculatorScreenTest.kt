package dima.lirazueuro

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun enterFiftyLiraAndCheckEuroConversion() {
        // Wait for the UI to be ready
        composeTestRule.waitForIdle()

        // Verify we start with the Lira section selected
        composeTestRule.onNodeWithTag("lira_section").assertExists()
        composeTestRule.onNodeWithTag("lira_label").assertExists()

        // Enter 5 by clicking the 5 button
        composeTestRule.onNodeWithTag("button_5").performClick()

        // Enter 0 by clicking the 0 button
        composeTestRule.onNodeWithTag("button_0").performClick()

        // Verify that 50 is displayed in the Lira section
        composeTestRule.onNodeWithTag("lira_value").assertTextContains("50")

        // Verify that the Euro conversion (approximately 1.20) is displayed in the Euro section
        composeTestRule.onNodeWithTag("euro_value").assertTextContains("1.20")

        // Click on the Euro section to switch to Euro view
        composeTestRule.onNodeWithTag("euro_section").performClick()
        composeTestRule.waitForIdle()

        // Verify that the Euro section is now selected and displays the correct values
        // The Euro value should be 1.20 and the Lira value should be 50.00
        composeTestRule.onNodeWithTag("euro_value").assertTextContains("1.20")
        composeTestRule.onNodeWithTag("lira_value").assertTextContains("50.00")
    }

    @Test
    fun testDecimalAndBackspaceButtons() {
        // Wait for the UI to be ready
        composeTestRule.waitForIdle()

        // Enter 5 by clicking the 5 button
        composeTestRule.onNodeWithTag("button_5").performClick()

        // Click the decimal button
        composeTestRule.onNodeWithTag("button_decimal").performClick()

        // Enter 2 by clicking the 2 button
        composeTestRule.onNodeWithTag("button_2").performClick()

        // Verify that 5.2 is displayed in the Lira section
        composeTestRule.onNodeWithTag("lira_value").assertTextContains("5.2")

        // Verify that the Euro conversion is displayed correctly
        composeTestRule.onNodeWithTag("euro_value").assertTextContains("0.12")

        // Test the backspace button
        composeTestRule.onNodeWithTag("button_backspace").performClick()

        // Verify that 5. is displayed in the Lira section
        composeTestRule.onNodeWithTag("lira_value").assertTextContains("5")
    }
}
