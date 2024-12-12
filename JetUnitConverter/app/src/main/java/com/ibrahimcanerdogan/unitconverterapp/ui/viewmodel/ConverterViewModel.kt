package com.ibrahimcanerdogan.unitconverterapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.unitconverterapp.data.database.ConversionEntity
import com.ibrahimcanerdogan.unitconverterapp.data.model.Conversion
import com.ibrahimcanerdogan.unitconverterapp.data.repository.ConverterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val converterRepository: ConverterRepository
) : ViewModel() {

    val selectedConversion : MutableState<Conversion?> = mutableStateOf(null)
    val inputText : MutableState<String> = mutableStateOf("")
    val typedValue = mutableStateOf("0.0")

    fun getAllConversionsData() = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2,"Kilograms to Pounds","kg","lbs",2.20462),
        Conversion(3,"Yards to Meters","yd","m",0.9144),
        Conversion(4,"Meters to Yards","m","yd",1.09361),
        Conversion(5,"Miles to Kilometers","mi","km",1.60934),
        Conversion(6,"Kilometers to Miles","km","mi",0.621371)
    )

    val resultList = converterRepository.getAllResultDatabase()

    fun addConversionResult(baseType: String, convertType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.addResultDatabase(ConversionEntity(0, baseType, convertType))
        }
    }

    fun removeSelectedConversion(entity: ConversionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteResultDatabase(entity)
        }
    }

    fun clearAllConversionHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            converterRepository.deleteAllResultDatabase()
        }
    }

}