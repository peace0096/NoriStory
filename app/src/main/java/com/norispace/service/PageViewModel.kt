package com.norispace.service

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {
    val page = MutableLiveData<Int>()
    init {
        page.value = 1
    }
}