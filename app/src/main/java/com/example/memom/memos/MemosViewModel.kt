package com.example.memom.memos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.example.memom.data.entity.MemoItem
import com.example.memom.data.repository.MemosRepository
import kotlinx.coroutines.launch

class MemosViewModel @ViewModelInject constructor(
    private val memosRepository: MemosRepository
) : ViewModel() {

    private val _memoList = MutableLiveData<List<MemoItem>>()
    val memoList: LiveData<List<MemoItem>> = _memoList.distinctUntilChanged()

    fun fetchMemoList() {
        viewModelScope.launch {
            runCatching { memosRepository.getMemoList() }
                .onSuccess {
                    _memoList.value = it
                }
                .onFailure {
                    it.printStackTrace()
                    throw it
                }
        }
    }
}