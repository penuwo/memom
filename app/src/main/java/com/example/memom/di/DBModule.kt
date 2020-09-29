package com.example.memom.di

import android.content.Context
import androidx.room.Room
import com.example.memom.data.dao.MemoDao
import com.example.memom.data.db.MemoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DBModule {

    @Provides
    fun provideMemoDao(memoDatabase: MemoDatabase): MemoDao {
        return memoDatabase.memoDao
    }

    @Singleton
    @Provides
    fun provideMemoDataBase(@ApplicationContext context: Context): MemoDatabase {
        return Room.databaseBuilder(context, MemoDatabase::class.java, MemoDatabase.DB_NAME).build()
    }
}