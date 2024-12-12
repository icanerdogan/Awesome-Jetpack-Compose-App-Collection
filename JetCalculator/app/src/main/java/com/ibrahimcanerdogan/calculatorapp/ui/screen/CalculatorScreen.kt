package com.ibrahimcanerdogan.calculatorapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.calculatorapp.ui.component.CalculatorButton
import com.ibrahimcanerdogan.calculatorapp.ui.component.CalculatorResultText
import com.ibrahimcanerdogan.calculatorapp.ui.component.ThemeSwitcher
import com.ibrahimcanerdogan.calculatorapp.ui.theme.ButtonSpaceSize
import com.ibrahimcanerdogan.calculatorapp.ui.viewmodel.CalculatorViewModel
import com.ibrahimcanerdogan.calculatorapp.util.CalculatorAction
import com.ibrahimcanerdogan.calculatorapp.util.CalculatorOperation

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val state = viewModel.state

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(10.dp)
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Box(modifier = Modifier.align(Alignment.TopEnd)) {
                ThemeSwitcher(
                    darkTheme = darkTheme,
                    size = 30.dp,
                    padding = 5.dp,
                    onClick = onThemeUpdated
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
            ) {
                CalculatorResultText(state = state)

                Divider(
                    modifier = Modifier
                        .height(0.5.dp)
                        .background(MaterialTheme.colorScheme.onSurface)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
                    ) {
                        CalculatorDeleteButtonGroup(viewModel)
                        CalculatorNumberButtonGroup(viewModel)
                    }
                    Spacer(modifier = Modifier
                        .fillMaxHeight()
                        .width(ButtonSpaceSize))
                    CalculatorOperationButtonGroup(viewModel)
                }
            }

        }
    }
}

@Composable
fun CalculatorOperationButtonGroup(viewModel: CalculatorViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
    ) {
        CalculatorButton(
            symbol = "/",
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        }

        CalculatorButton(
            symbol = "x",
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        }

        CalculatorButton(
            symbol = "-",
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
        }

        CalculatorButton(
            symbol = "+",
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
        }

        CalculatorButton(
            symbol = "=",
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Calculate)
        }
    }
}

@Composable
fun CalculatorNumberButtonGroup(viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
    ) {
        CalculatorButton(
            symbol = "7",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(7))
        }
        CalculatorButton(
            symbol = "8",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(8))
        }
        CalculatorButton(
            symbol = "9",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(9))
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
    ) {
        CalculatorButton(
            symbol = "4",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(4))
        }
        CalculatorButton(
            symbol = "5",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(5))
        }
        CalculatorButton(
            symbol = "6",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(6))
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
    ) {
        CalculatorButton(
            symbol = "1",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(1))
        }
        CalculatorButton(
            symbol = "2",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(2))
        }
        CalculatorButton(
            symbol = "3",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Number(3))
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
    ) {
        CalculatorButton(
            symbol = "0",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(2f)
                .weight(2f)
        ) {
            viewModel.onAction(CalculatorAction.Number(0))
        }
        CalculatorButton(
            symbol = ".",
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Decimal)
        }
    }
}

@Composable
fun CalculatorDeleteButtonGroup(viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(ButtonSpaceSize)
    ) {
        CalculatorButton(
            symbol = "AC",
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.aspectRatio(2f)
                .weight(2f)
        ) {
            viewModel.onAction(CalculatorAction.Clear)
        }
        CalculatorButton(
            symbol = "Del",
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.aspectRatio(1f)
                .weight(1f)
        ) {
            viewModel.onAction(CalculatorAction.Delete)
        }
    }
}
