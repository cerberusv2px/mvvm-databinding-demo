package com.v2px.mvvmcaster.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

open class RestaurantCalculator(val repository: TipCalculationRepository = TipCalculationRepository()) {

    fun calculateTip(checkInput: Double, tipPctInput: Int): TipCalculation {
        val tipAmount = (checkInput * (tipPctInput.toDouble() / 100.0))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()
        val grandTotal = checkInput + tipAmount
        return TipCalculation(
                checkAmount = checkInput,
                tipPct = tipPctInput,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculationByLocation(locationName: String): TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}