package com.example.memom.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.memom.data.dao.MemoDao
import com.example.memom.data.entity.MemoItem

@Database(entities = [MemoItem::class], version = 1, exportSchema = false)
abstract class MemoDatabase : RoomDatabase() {

    abstract val memoDao: MemoDao

    companion object {
        const val DB_NAME = "memo_database"
    }
}