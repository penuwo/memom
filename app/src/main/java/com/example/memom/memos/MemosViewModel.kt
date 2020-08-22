package com.example.memom.memos

import android.util.Log
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

    fun moveMemoItem(fromPosition: Int, toPosition: Int) {
        _memoList.value?.toMutableList()?.let {
            it.add(toPosition, it.removeAt(fromPosition))
            _memoList.value = it
        }
    }

    fun removeMemoItem(position: Int): MemoItem? {
        _memoList.value?.toMutableList()?.let {
            val removedItem = it.removeAt(position)
            _memoList.value = it
            return removedItem
        } ?: return null
    }

    fun addMemoItem(position: Int, item: MemoItem) {
        _memoList.value?.toMutableList()?.let {
            it.add(position, item)
            _memoList.value = it
        }
    }

    fun onMemoItemClicked() {
        Log.d("TAG", "memoItemClicked: ")
    }
}