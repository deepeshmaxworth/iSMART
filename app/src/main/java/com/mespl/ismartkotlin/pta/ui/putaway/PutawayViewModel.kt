package com.mespl.ismartkotlin.pta.ui.putaway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PutawayViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is putaway Fragment"
    }
    val text: LiveData<String> = _text
}