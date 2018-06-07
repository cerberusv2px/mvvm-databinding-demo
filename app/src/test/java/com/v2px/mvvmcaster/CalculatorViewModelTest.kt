package com.v2px.mvvmcaster

import android.app.Application
import com.v2px.mvvmcaster.model.RestaurantCalculator
import com.v2px.mvvmcaster.model.TipCalculation
import com.v2px.mvvmcaster.viewmodel.CaculatorViewModel
import junit.framework.Assert.assertEquals

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CaculatorViewModel

    @Mock
    lateinit var mockCalculator: RestaurantCalculator

    @Mock
    lateinit var application: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        stubResource(0.0, "$0.00")
        calculatorViewModel = CaculatorViewModel(application, mockCalculator)
    }

    private fun stubResource(give: Double, returnStub: String) {
        `when`(application.getString(R.string.dollar_amount, give)).thenReturn(returnStub)
    }

  /*  @Test
    fun testCalculateTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"
        calculatorViewModel.calculateTip()

        assertEquals(10.00, calculatorViewModel.outputCheckAmount)
        assertEquals(1.50, calculatorViewModel.outputTipAmount)
        assertEquals(11.50, calculatorViewModel.outputTotalDollarAmount)
    }*/

    @Test
    fun testCalculateMockTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")

        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)

    }

    @Test
    fun testCalculateTipBadTipPercent() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""
        calculatorViewModel.calculateTip()
        verify(mockCalculator, never()).calculateTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())
    }
}