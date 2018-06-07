package com.v2px.mvvmcaster


import com.v2px.mvvmcaster.model.RestaurantCalculator
import com.v2px.mvvmcaster.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResturantCalculatorTest {

    lateinit var calculator: RestaurantCalculator

    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculateTip() {

        val baseTc = TipCalculation(checkAmount = 10.00)

        val testVals = listOf(
                baseTc.copy(tipPct = 25, tipAmount = 2.5, grandTotal = 12.50),
                baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80))

        testVals.forEach {
            assertEquals(it,calculator.calculateTip(it.checkAmount, it.tipPct))
        }

    }

}