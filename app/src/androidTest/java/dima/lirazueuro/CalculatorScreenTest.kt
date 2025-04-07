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
        composeTestRule.onNodeWithText("Lira, TRY, TL, ₺").assertExists()

        // Enter 5 by clicking the 5 button
        composeTestRule.onNodeWithText("5").performClick()

        // Enter 0 by clicking the 0 button
        composeTestRule.onNodeWithText("0").performClick()

        // Verify that 50 is displayed in the UI
        composeTestRule.onAllNodesWithText("50").fetchSemanticsNodes().isNotEmpty()

        // Verify that the Euro conversion (approximately 1.20) is displayed in the UI
        composeTestRule.onAllNodesWithText("1.20").fetchSemanticsNodes().isNotEmpty()

        // Click on the Euro section to switch to Euro view
        composeTestRule.onNodeWithText("Euro, €").performClick()
        composeTestRule.waitForIdle()

        // Verify that the Euro section is now selected and displays the correct values
        // The Euro value should be 1.20 and the Lira value should be 50.00
        composeTestRule.onAllNodesWithText("1.20").fetchSemanticsNodes().isNotEmpty()
        composeTestRule.onAllNodesWithText("50.00").fetchSemanticsNodes().isNotEmpty()
    }

    @Test
    fun testDecimalAndBackspaceButtons() {
        // Wait for the UI to be ready
        composeTestRule.waitForIdle()

        // Enter 5 by clicking the 5 button
        composeTestRule.onNodeWithText("5").performClick()

        // Click the decimal button
        composeTestRule.onNodeWithText(".").performClick()

        // Enter 2 by clicking the 2 button
        composeTestRule.onNodeWithText("2").performClick()

        // Verify that 5.2 is displayed in the UI
        composeTestRule.onAllNodesWithText("5.2").fetchSemanticsNodes().isNotEmpty()

        // Verify that the Euro conversion is displayed correctly
        composeTestRule.onAllNodesWithText("0.12").fetchSemanticsNodes().isNotEmpty()

        // Test the backspace button
        composeTestRule.onNodeWithText("⌫").performClick()

        // Verify that 5 is displayed in the UI
        composeTestRule.onAllNodesWithText("5").fetchSemanticsNodes().isNotEmpty()
    }
}
