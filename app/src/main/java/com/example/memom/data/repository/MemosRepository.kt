package com.example.memom.data.repository

import com.example.memom.data.entity.MemoItem
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class MemosRepository @Inject constructor() {

    suspend fun getMemoList(): List<MemoItem> {
        delay(100L)
        return List(30) {
            MemoItem(Random.nextInt().toString())
        }
    }
}