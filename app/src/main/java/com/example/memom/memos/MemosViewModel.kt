package com.example.memom.memos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import kotlin.random.Random

class MemosViewModel @ViewModelInject constructor() : ViewModel() {

    val text = liveData {
        while (true) {
            emit(Random.nextInt().toString())
            delay(500L)
        }
    }
}