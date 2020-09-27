package com.example.memom.add

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memom.data.entity.MemoItem

class AddViewModel @ViewModelInject constructor() : ViewModel() {

    val memoText = MutableLiveData<String>()

    private val _addItem = MutableLiveData<MemoItem>()
    val addItem: LiveData<MemoItem> get() = _addItem

    fun saveMemoItem() {
        memoText.value?.let {
            _addItem.value = MemoItem(it)
        }
    }
}