package com.v2px.mvvmcaster.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.v2px.mvvmcaster.R
import com.v2px.mvvmcaster.model.RestaurantCalculator
import com.v2px.mvvmcaster.model.TipCalculation

open class CaculatorViewModel(val app: Application, val calculator: RestaurantCalculator = RestaurantCalculator()) : BaseObservable() {

    var inputCheckAmount = ""

    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = app.getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalDollarAmount = app.getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()
        println("Check Amount: $checkAmount \t Tip Percentage: $tipPct")

        if (checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            clearInputs()
        }
    }

    private fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }

}