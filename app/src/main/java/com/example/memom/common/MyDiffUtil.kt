package com.example.memom.common

import androidx.recyclerview.widget.DiffUtil

private class Callback<T>(
    val old: List<T>,
    val new: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size
    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
        = old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
        = old[oldItemPosition] == new[newItemPosition]
}

fun <T> calculateDiff(
    old: List<T>,
    new: List<T>,
    detectMoves: Boolean = true
): DiffUtil.DiffResult = DiffUtil.calculateDiff(Callback(old, new), detectMoves)
