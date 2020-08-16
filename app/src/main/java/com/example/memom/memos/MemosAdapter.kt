package com.example.memom.memos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.memom.R
import com.example.memom.common.calculateDiff
import com.example.memom.data.entity.MemoItem
import com.example.memom.databinding.MemoItemBinding
import kotlin.properties.Delegates

class MemosAdapter(
    lifecycleOwner: LifecycleOwner,
    private val viewModel: MemosViewModel
) : RecyclerView.Adapter<MemosAdapter.MemosViewHolder>() {

    private var memoList: List<MemoItem> by Delegates.observable(emptyList()) { _, o, n ->
        calculateDiff(o, n).dispatchUpdatesTo(this)
    }

    init {
        viewModel.memoList.observe(lifecycleOwner, Observer { memoList = it })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemosViewHolder {
        val binding = DataBindingUtil.inflate<MemoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.memo_item,
            parent,
            false
        )
        return MemosViewHolder(binding)
    }

    override fun getItemCount() = memoList.size

    override fun onBindViewHolder(holder: MemosViewHolder, position: Int) {
        holder.binding.let {
            it.viewModel = viewModel
            it.item = memoList[position]
            it.executePendingBindings()
        }
    }

    class MemosViewHolder(val binding: MemoItemBinding) : RecyclerView.ViewHolder(binding.root)
}