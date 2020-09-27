package com.example.memom.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemoItem(
    val text: String
) : Parcelable