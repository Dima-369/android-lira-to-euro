package dima.lirazueuro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

private val selectedLabelColor = TailwindCssColors.violet700

data class CalculatorState(
    val isLiraSelected: Boolean = true,
    val liraAmount: String = "",
    val euroAmount: String = ""
)

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(CalculatorState()) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { state = state.copy(isLiraSelected = true) }
                    .padding(16.dp)
                    .testTag("lira_section")
            ) {
                Text(
                    text = "Lira, TRY, TL",
                    fontSize = 40.sp,
                    textAlign = TextAlign.Start,
                    color = if (state.isLiraSelected) selectedLabelColor else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .testTag("lira_label")
                )
                Text(
                    text = state.liraAmount.ifEmpty { "0" } + " ₺",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Start,
                    color = if (state.liraAmount.isEmpty()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .testTag("lira_value")
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { state = state.copy(isLiraSelected = false) }
                    .testTag("euro_section"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Euro",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Start,
                        color = if (!state.isLiraSelected) selectedLabelColor else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .testTag("euro_label")
                    )
                    Text(
                        text = state.euroAmount.ifEmpty { "0" } + " €",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Start,
                        color = if (state.euroAmount.isEmpty()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .testTag("euro_value")
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = { 
                    state = state.copy(liraAmount = "", euroAmount = "")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag("reset_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text("Reset", fontSize = 20.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NumberButton(number = "7", testTag = "button_7") {
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "7"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 7)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
                NumberButton(number = "8", testTag = "button_8") {
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "8"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 8)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
                NumberButton(number = "9", testTag = "button_9") {
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "9"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 9)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                NumberButton(number = "4", testTag = "button_4") { 
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "4"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 4)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
                NumberButton(number = "5", testTag = "button_5") { 
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "5"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 5)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
                NumberButton(number = "6", testTag = "button_6") { 
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "6"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 6)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NumberButton(number = "1", testTag = "button_1") { 
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "1"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 1)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
                NumberButton(number = "2", testTag = "button_2") { 
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "2"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 2)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
                NumberButton(number = "3", testTag = "button_3") { 
                    if (state.isLiraSelected) {
                        val newLira = state.liraAmount + "3"
                        state = state.copy(
                            liraAmount = newLira,
                            euroAmount = convertLiraToEuro(newLira)
                        )
                    } else {
                        val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                        val newEuro = String.format(Locale.US, "%.2f", current * 10 + 3)
                        state = state.copy(
                            euroAmount = newEuro,
                            liraAmount = convertEuroToLira(newEuro)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (state.isLiraSelected) {
                            val newLira = state.liraAmount + "0"
                            state = state.copy(
                                liraAmount = newLira,
                                euroAmount = convertLiraToEuro(newLira)
                            )
                        } else {
                            val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                            val newEuro = String.format(Locale.US, "%.2f", current * 10)
                            state = state.copy(
                                euroAmount = newEuro,
                                liraAmount = convertEuroToLira(newEuro)
                            )
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(60.dp)
                        .testTag("button_0")
                ) {
                    Text("0", fontSize = 24.sp)
                }
                Button(
                    onClick = {
                        if (state.isLiraSelected && !state.liraAmount.contains(".")) {
                            state = state.copy(liraAmount = state.liraAmount + ".")
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(60.dp)
                        .testTag("button_decimal")
                ) {
                    Text(".", fontSize = 24.sp)
                }
                Button(
                    onClick = {
                        if (state.isLiraSelected) {
                            if (state.liraAmount.isNotEmpty()) {
                                val newLira = state.liraAmount.dropLast(1)
                                state = state.copy(
                                    liraAmount = newLira,
                                    euroAmount = convertLiraToEuro(newLira)
                                )
                            }
                        } else {
                            if (state.euroAmount.isNotEmpty()) {
                                val current = if (state.euroAmount.isEmpty()) 0.0 else state.euroAmount.toDouble()
                                val newEuro = String.format(Locale.US, "%.2f", current / 10)
                                state = state.copy(
                                    euroAmount = newEuro,
                                    liraAmount = convertEuroToLira(newEuro)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(60.dp)
                        .testTag("button_backspace"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("⌫", fontSize = 24.sp)
                }
            }
        }
    }
}

private fun convertLiraToEuro(liraAmount: String): String {
    return try {
        val lira = liraAmount.toDouble()
        val euro = lira / 41.6
        String.format(Locale.US, "%.2f", euro)
    } catch (e: NumberFormatException) {
        ""
    }
}

private fun convertEuroToLira(euroAmount: String): String {
    return try {
        val euro = euroAmount.toDouble()
        val lira = euro * 41.6
        String.format(Locale.US, "%.2f", lira)
    } catch (e: NumberFormatException) {
        ""
    }
}
