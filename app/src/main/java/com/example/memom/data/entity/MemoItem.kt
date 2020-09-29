package com.example.memom.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class MemoItem(
    @PrimaryKey @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "text") val text: String
) : Parcelable