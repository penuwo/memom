package com.example.memom.memos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.memom.R
import com.example.memom.common.Binding
import com.example.memom.databinding.FragmentMemosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemosFragment : Fragment(R.layout.fragment_memos) {

    private val viewModel: MemosViewModel by viewModels()
    private val binding: FragmentMemosBinding by Binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.viewModel = viewModel
            it.memosRecyclerView.layoutManager = LinearLayoutManager(context)
            it.memosRecyclerView.adapter = MemosAdapter(viewLifecycleOwner, viewModel)
            ItemTouchHelper(
                object : SimpleCallback(UP or DOWN, LEFT or RIGHT) {

                    override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                        viewModel.moveMemoItem(viewHolder.adapterPosition, target.adapterPosition)
                        return true
                    }

                    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                        viewModel.removeMemoItem(viewHolder.adapterPosition)
                    }
                }
            ).attachToRecyclerView(it.memosRecyclerView)
        }
        viewModel.fetchMemoList()
    }
}