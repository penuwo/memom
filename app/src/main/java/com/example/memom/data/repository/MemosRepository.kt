package com.example.memom.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memom.data.dao.MemoDao
import com.example.memom.data.entity.MemoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemosRepository @Inject constructor(private val memoDao: MemoDao) {

    fun getMemoListByDateAscending(): LiveData<List<MemoItem>> = memoDao.getMemoListByDateAscending()

    fun getMemoListByDateDescending(): LiveData<List<MemoItem>> = memoDao.getMemoListByDateDescending()

    suspend fun addMemoItem(memoItem: MemoItem) {
        withContext(Dispatchers.IO) {
            memoDao.insert(memoItem)
        }
    }

    suspend fun deleteMemoItem(memoItem: MemoItem) {
        withContext(Dispatchers.IO) {
            memoDao.delete(memoItem)
        }
    }
}