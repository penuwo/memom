package com.example.memom.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.memom.data.entity.MemoItem
import javax.inject.Singleton

@Singleton
@Dao
interface MemoDao {

    @Query("SELECT * from MemoItem ORDER BY date ASC")
    fun getMemoListByDateAscending() : LiveData<List<MemoItem>>

    @Query("SELECT * from MemoItem ORDER BY date DESC")
    fun getMemoListByDateDescending() : LiveData<List<MemoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(memoItem: MemoItem)

    @Delete
    suspend fun delete(memoItem: MemoItem)
}