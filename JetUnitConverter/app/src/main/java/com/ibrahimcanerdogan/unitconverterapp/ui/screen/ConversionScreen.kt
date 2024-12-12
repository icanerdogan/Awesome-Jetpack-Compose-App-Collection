package com.ibrahimcanerdogan.unitconverterapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ibrahimcanerdogan.unitconverterapp.ui.components.converter.ConverterScreen
import com.ibrahimcanerdogan.unitconverterapp.ui.components.history.HistoryScreen
import com.ibrahimcanerdogan.unitconverterapp.ui.viewmodel.ConverterViewModel
import com.ibrahimcanerdogan.unitconverterapp.ui.viewmodel.ConverterViewModelFactory

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    factory: ConverterViewModelFactory,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
) {
    val listConversionType = converterViewModel.getAllConversionsData()
    val listHistory = converterViewModel.resultList.collectAsState(initial = emptyList())

    Column(
        modifier = modifier.padding(30.dp)
    ) {
        ConverterScreen(
            list = listConversionType,
            selectedConversion = converterViewModel.selectedConversion,
            inputText = converterViewModel.inputText,
            typedValue = converterViewModel.typedValue
        ) { baseType, convertType ->
            converterViewModel.addConversionResult(baseType, convertType)
        }
        Spacer(modifier = modifier.height(20.dp))
        HistoryScreen(
            historyList = listHistory,
            onCloseTask = {
                converterViewModel.removeSelectedConversion(it)
            },
            onClearAllTask =  {
                converterViewModel.clearAllConversionHistory()
            }
        )
    }
}