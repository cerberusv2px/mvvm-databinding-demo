package com.v2px.mvvmcaster.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.v2px.mvvmcaster.R
import com.v2px.mvvmcaster.model.RestaurantCalculator
import com.v2px.mvvmcaster.model.TipCalculation

open class CaculatorViewModel @JvmOverloads constructor(
        app: Application, val calculator: RestaurantCalculator = RestaurantCalculator()
) : ObservableViewModel(app) {

    private var lastTipCalcilated = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalcilated.checkAmount)
    val outputTipAmount get()= getApplication<Application>().getString(R.string.dollar_amount, lastTipCalcilated.tipAmount)
    val outputTotalDollarAmount get()= getApplication<Application>().getString(R.string.dollar_amount, lastTipCalcilated.grandTotal)
    val locationName get() = lastTipCalcilated.locationName

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalcilated = tc
        notifyChange()
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalcilated.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
    }

    private fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }

}