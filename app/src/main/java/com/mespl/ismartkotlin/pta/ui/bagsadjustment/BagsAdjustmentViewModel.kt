package com.mespl.ismartkotlin.pta.ui.bagsadjustment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BagsAdjustmentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is BagsAdjustment Fragment"
    }
    val text: LiveData<String> = _text
}