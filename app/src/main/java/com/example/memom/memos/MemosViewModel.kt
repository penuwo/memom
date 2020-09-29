package com.example.memom.memos

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.memom.data.entity.MemoItem
import com.example.memom.data.repository.MemosRepository
import com.example.memom.data.repository.SortPreferenceRepository
import com.example.memom.data.repository.SortType
import kotlinx.coroutines.launch

class MemosViewModel @ViewModelInject constructor(
    private val memosRepository: MemosRepository,
    sortPreferenceRepository: SortPreferenceRepository
) : ViewModel() {

    val memoList: LiveData<List<MemoItem>> = sortPreferenceRepository.sortPreferenceLiveData.switchMap {
        when (it) {
            SortType.DateAscending -> memosRepository.getMemoListByDateAscending()
            SortType.DateDescending -> memosRepository.getMemoListByDateDescending()
        }
    }

    fun removeMemoItemAt(position: Int): MemoItem? {
        return memoList.value?.get(position)?.also {
            viewModelScope.launch {
                memosRepository.deleteMemoItem(it)
            }
        }
    }

    fun addMemoItem(item: MemoItem) {
        viewModelScope.launch {
            memosRepository.addMemoItem(item)
        }
    }

    fun onMemoItemClicked() {
        Log.d("TAG", "memoItemClicked: ")
    }
}