package com.example.converterunit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.converterunit.ui.theme.ConverterUnitTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterUnitTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableDoubleStateOf(1.00) }
    var oConversionFactor = remember { mutableDoubleStateOf(1.00) }


    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0/oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("Unit Converter",
                style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(inputValue, {
            inputValue = it
            convertUnit()
        },
            label = { Text("Enter value") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box {
                Button(onClick =  { iExpanded = true } ) {
            Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        "Arrow Down")
        }

                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem({ Text("Centemeters") },
                        {
                            iExpanded = false
                            inputUnit = "Centemeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnit()

                        }
                    )
                    DropdownMenuItem({ Text("Meters") },
                        {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem({ Text("Feet") },
                        { iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem({ Text("Milimeters") },
                        { iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Box {
                Button(onClick =  { oExpanded = true } ) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        "Arrow Down")
                }

                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false } ) {
                    DropdownMenuItem( { Text("Centemeters") },
                        { oExpanded = false
                            outputUnit = "Centemeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem( { Text("Meters") },
                        { oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.00
                            convertUnit()
                        }
                    )
                    DropdownMenuItem( { Text("Feet") },
                        { oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem( { Text("Milimeters") },
                        { oExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}


@Preview(apiLevel = 34)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}