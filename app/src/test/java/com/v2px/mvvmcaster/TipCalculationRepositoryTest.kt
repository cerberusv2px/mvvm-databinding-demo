package com.v2px.mvvmcaster

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.v2px.mvvmcaster.model.TipCalculation
import com.v2px.mvvmcaster.model.TipCalculationRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    lateinit var repository: TipCalculationRepository

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation(
                checkAmount = 100.0,
                tipPct = 25,
                tipAmount = 25.0,
                grandTotal = 125.0,
                locationName = "Pancake Paradise"
        )
        repository.saveTipCalculation(tip)
        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testLoadSavedTipCalculations() {
        val tip1 = TipCalculation(
                checkAmount = 100.0,
                tipPct = 25,
                tipAmount = 25.0,
                grandTotal = 125.0,
                locationName = "Pancake Paradise"
        )

        val tip2 = TipCalculation(
                checkAmount = 100.0,
                tipPct = 25,
                tipAmount = 25.0,
                grandTotal = 125.0,
                locationName = "Veg"
        )

        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        repository.loadSavedTipCalculations().observeForever { tipCalculations ->
            assertEquals(2, tipCalculations?.size)
        }
    }
}