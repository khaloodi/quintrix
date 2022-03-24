package com.example.dollarconverter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val usd_to_eu_rate = 0.74f

    private var dollarText = ""

//    private var result: Float = 0f
    private var result : MutableLiveData<Float> = MutableLiveData()

    fun setAmount(value: String) {
        this.dollarText = value

        result.setValue(value.toFloat() * usd_to_eu_rate)
    }

    fun getResult(): MutableLiveData<Float> {
        return result
    }
}