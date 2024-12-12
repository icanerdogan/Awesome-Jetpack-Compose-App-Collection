package com.ibrahimcanerdogan.tipcalculatorapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.tipcalculatorapp.ui.widgets.BillInputTextField
import com.ibrahimcanerdogan.tipcalculatorapp.ui.widgets.RoundIconButton
import com.ibrahimcanerdogan.tipcalculatorapp.util.CalculateUtils


@Preview
@Composable
fun MainPage(
    modifier: Modifier = Modifier
) {
    val totalPerPersonState = remember { mutableDoubleStateOf(0.0) }
    TotalPerPersonCard(totalPerPersonState = totalPerPersonState.value)
    BillTipCard(totalPerPersonState = totalPerPersonState)
}

@Composable
private fun TotalPerPersonCard(
    totalPerPersonState: Double = 0.0
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(CornerSize(20.dp))),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val formatTotalPerPerson = "%.2f".format(totalPerPersonState)

            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$$formatTotalPerPerson",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun BillTipCard(
    modifier: Modifier = Modifier,
    totalPerPersonState: MutableDoubleState
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val totalBillState = remember { mutableStateOf("") }
    val validState = remember(totalBillState.value) { totalBillState.value.trim().isNotEmpty() }

    val splitByState = remember { mutableIntStateOf(1) }

    val sliderPositionState = remember { mutableFloatStateOf(0f) }
    val tipPercentage = (sliderPositionState.floatValue * 100).toInt()
    val tipAmountState = remember { mutableDoubleStateOf(0.0) }

    Surface(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = modifier
                .padding(6.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            BillInputTextField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    totalPerPersonState.doubleValue = CalculateUtils.calculateTotalPerPerson(
                        totalBillState.value.toDouble(),
                        splitByState.intValue,
                        tipPercentage
                    )
                    keyboardController?.hide()
                }
            )

            BillSplitRow(
                validState = validState,
                splitByState = splitByState,
                totalPerPersonState = totalPerPersonState,
                totalBillState = totalBillState,
                tipPercentage = tipPercentage
            )

            TipContent(
                sliderPositionState = sliderPositionState,
                validState = validState,
                totalBillState = totalBillState,
                tipPercentage = tipPercentage,
                tipAmountState = tipAmountState,
                totalPerPersonState = totalPerPersonState,
                splitByState = splitByState
            )
        }
    }
}

@Composable
private fun BillSplitRow(
    modifier: Modifier = Modifier,
    validState: Boolean,
    splitByState: MutableIntState,
    totalPerPersonState: MutableDoubleState,
    totalBillState: MutableState<String>,
    tipPercentage: Int
) {
    val range = IntRange(start = 1, endInclusive = 100)

    if (validState) {
        Row(
            modifier = modifier.padding(3.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Split",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(120.dp))
            Row(
                modifier = modifier.padding(horizontal = 3.dp),
                horizontalArrangement = Arrangement.End
            ) {
                RoundIconButton(
                    imageVector = Icons.Default.Remove,
                    onClick = {
                        splitByState.intValue =
                            if (splitByState.intValue > 1) splitByState.value - 1 else 1
                        totalPerPersonState.doubleValue = CalculateUtils.calculateTotalPerPerson(
                            totalBillState.value.toDouble(),
                            splitByState.intValue,
                            tipPercentage
                        )
                    }
                )
                Text(
                    text = "${splitByState.intValue}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    ),
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp, end = 20.dp)
                )
                RoundIconButton(
                    imageVector = Icons.Default.Add,
                    onClick = {
                        if (splitByState.intValue < range.last) {
                            splitByState.intValue += 1
                            totalPerPersonState.doubleValue =
                                CalculateUtils.calculateTotalPerPerson(
                                    totalBillState.value.toDouble(),
                                    splitByState.intValue,
                                    tipPercentage
                                )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun TipContent(
    sliderPositionState: MutableFloatState,
    validState: Boolean,
    totalBillState: MutableState<String>,
    tipPercentage: Int,
    tipAmountState: MutableDoubleState,
    totalPerPersonState: MutableDoubleState,
    splitByState: MutableIntState
) {
    if (validState) {
        TipRow(tipAmountState = tipAmountState)
        TipBar(
            totalBillState = totalBillState,
            sliderPositionState = sliderPositionState,
            tipPercentage = tipPercentage,
            tipAmountState = tipAmountState,
            totalPerPersonState = totalPerPersonState,
            splitByState = splitByState
        )
    }
}

@Composable
private fun TipRow(
    modifier: Modifier = Modifier,
    tipAmountState: MutableDoubleState
) {
    Row(
        modifier = modifier.padding(horizontal = 3.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Tip",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.width(180.dp))

        Text(
            text = "$${tipAmountState.doubleValue}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),
            modifier = modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun TipBar(
    modifier: Modifier = Modifier,
    totalBillState: MutableState<String>,
    sliderPositionState: MutableFloatState,
    tipPercentage: Int,
    tipAmountState: MutableDoubleState,
    totalPerPersonState: MutableDoubleState,
    splitByState: MutableIntState
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$tipPercentage%")

        Slider(
            modifier = modifier.padding(horizontal = 15.dp),
            value = sliderPositionState.floatValue,
            onValueChange = {
                sliderPositionState.floatValue = it
            }, onValueChangeFinished = {
                tipAmountState.doubleValue =
                    CalculateUtils.calculateTotalTip(totalBillState.value.toDouble(), tipPercentage)
                totalPerPersonState.doubleValue = CalculateUtils.calculateTotalPerPerson(
                    totalBillState.value.toDouble(),
                    splitByState.intValue,
                    tipPercentage
                )
            }
        )
    }
}