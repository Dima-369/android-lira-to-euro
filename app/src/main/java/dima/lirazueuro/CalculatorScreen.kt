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

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var isLiraSelected by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isLiraSelected = true }
                    .padding(16.dp)
                    .testTag("lira_section")
            ) {
                Text(
                    text = "Lira, TRY, TL, ₺",
                    fontSize = 40.sp,
                    textAlign = TextAlign.Start,
                    color = if (isLiraSelected) selectedLabelColor else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .testTag("lira_label")
                )
                Text(
                    text = input.ifEmpty { "0" },
                    fontSize = 32.sp,
                    textAlign = TextAlign.Start,
                    color = if (input.isEmpty()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .testTag("lira_value")
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (!isLiraSelected) {
                            /*
                                                        try {
                                                            val value = input.toDouble()
                                                            val lira = value * 41.6
                                                            input = String.format(Locale.US, "%.2f", lira)
                                                        } catch (e: NumberFormatException) {
                                                            // Keep the current input if conversion fails
                                                        }
                            */
                        }
                        isLiraSelected = false
                    }
                    .testTag("euro_section")
                    ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Euro, €",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Start,
                        color = if (!isLiraSelected) selectedLabelColor else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .testTag("euro_label")
                    )
                    Text(
                        text = if (input.isNotEmpty()) {
                            try {
                                val value = input.toDouble()
                                if (isLiraSelected) {
                                    val euro = value / 41.6
                                    String.format(Locale.US, "%.2f", euro)
                                } else {
                                    val lira = value * 41.6
                                    String.format(Locale.US, "%.2f", lira)
                                }
                            } catch (e: NumberFormatException) {
                                "Invalid input"
                            }
                        } else "0",
                        fontSize = 32.sp,
                        textAlign = TextAlign.Start,
                        color = if (input.isEmpty()) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
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
                onClick = { input = "" },
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
                    if (isLiraSelected) {
                        input += "7"
                    } else {
                        input = try {
                            val current = if (input.isEmpty()) 0.0 else input.toDouble()
                            String.format(Locale.US, "%.2f", current * 10 + 7)
                        } catch (e: NumberFormatException) {
                            "7"
                        }
                    }
                }
                NumberButton(number = "8", testTag = "button_8") {
                    if (isLiraSelected) {
                        input += "8"
                    } else {
                        input = try {
                            val current = if (input.isEmpty()) 0.0 else input.toDouble()
                            String.format(Locale.US, "%.2f", current * 10 + 8)
                        } catch (e: NumberFormatException) {
                            "8"
                        }
                    }
                }
                NumberButton(number = "9", testTag = "button_9") {
                    if (isLiraSelected) {
                        input += "9"
                    } else {
                        input = try {
                            val current = if (input.isEmpty()) 0.0 else input.toDouble()
                            String.format(Locale.US, "%.2f", current * 10 + 9)
                        } catch (e: NumberFormatException) {
                            "9"
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                NumberButton(number = "4", testTag = "button_4") { input += "4" }
                NumberButton(number = "5", testTag = "button_5") { input += "5" }
                NumberButton(number = "6", testTag = "button_6") { input += "6" }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NumberButton(number = "1", testTag = "button_1") { input += "1" }
                NumberButton(number = "2", testTag = "button_2") { input += "2" }
                NumberButton(number = "3", testTag = "button_3") { input += "3" }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (isLiraSelected) {
                            input += "0"
                        } else {
                            input = try {
                                val current = if (input.isEmpty()) 0.0 else input.toDouble()
                                String.format(Locale.US, "%.2f", current * 10)
                            } catch (e: NumberFormatException) {
                                "0"
                            }
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
                        if (isLiraSelected && !input.contains(".")) {
                            input += "."
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
                        if (isLiraSelected) {
                            if (input.isNotEmpty()) input = input.dropLast(1)
                        } else {
                            input = try {
                                val current = if (input.isEmpty()) 0.0 else input.toDouble()
                                String.format(Locale.US, "%.2f", current / 10)
                            } catch (e: NumberFormatException) {
                                ""
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