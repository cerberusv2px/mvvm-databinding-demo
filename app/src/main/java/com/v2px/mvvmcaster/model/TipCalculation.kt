package com.v2px.mvvmcaster.model

data class TipCalculation(
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0,
        val locationName: String = ""
)