package com.mespl.ismartkotlin.pta.ui.dispatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DispatchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Dispatch Fragment"
    }
    val text: LiveData<String> = _text
}