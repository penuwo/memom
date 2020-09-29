package com.example.memom.memos

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.memom.R
import com.example.memom.add.AddBottomSheetDialogFragment
import com.example.memom.common.Binding
import com.example.memom.data.entity.MemoItem
import com.example.memom.databinding.FragmentMemosBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MemosFragment : Fragment(R.layout.fragment_memos) {

    private val viewModel: MemosViewModel by viewModels()
    private val binding: FragmentMemosBinding by Binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.viewModel = viewModel

            it.bottomAppBar.setNavigationOnClickListener {
                // TODO: Add navigation handle
            }
            it.bottomAppBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.option -> {
                        // TODO: Add option handle
                        true
                    }
                    else -> false
                }
            }

            it.fab.setOnClickListener {
                AddBottomSheetDialogFragment.show(childFragmentManager)
            }

            it.memosRecyclerView.layoutManager = LinearLayoutManager(context)
            it.memosRecyclerView.adapter = MemosAdapter(viewLifecycleOwner, viewModel)
            ItemTouchHelper(
                object : SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE, LEFT or RIGHT) {

                    override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder) = false

                    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                        viewModel.removeMemoItemAt(viewHolder.adapterPosition)?.let { removedItem ->
                            Snackbar.make(view, R.string.snack_bar_delete_text, Snackbar.LENGTH_LONG)
                                .setAnchorView(R.id.fab)
                                .setAction(R.string.undo_delete) { viewModel.addMemoItem(removedItem) }
                                .show()
                        }
                    }

                    override fun onChildDraw(
                        c: Canvas,
                        recyclerView: RecyclerView,
                        viewHolder: ViewHolder,
                        dX: Float,
                        dY: Float,
                        actionState: Int,
                        isCurrentlyActive: Boolean
                    ) {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                        if (actionState != ACTION_STATE_SWIPE) return
                        val marginVertical = resources.getDimension(R.dimen.memos_delete_icon_margin_vertical).roundToInt()
                        val marginHorizontal = resources.getDimension(R.dimen.memos_delete_icon_margin_horizontal).roundToInt()
                        val iconHeight = viewHolder.itemView.height - marginVertical * 2
                        val iconTint = requireContext().getColor(R.color.colorPrimary)
                        AppCompatResources.getDrawable(requireContext(), R.drawable.ic_delete)?.also { icon ->
                            icon.bounds = if (dX < 0) {
                                Rect(
                                    viewHolder.itemView.right - iconHeight - marginHorizontal,
                                    viewHolder.itemView.top + marginVertical,
                                    viewHolder.itemView.right - marginHorizontal,
                                    viewHolder.itemView.bottom - marginVertical
                                )
                            } else {
                                Rect(
                                    viewHolder.itemView.left + marginHorizontal,
                                    viewHolder.itemView.top + marginVertical,
                                    viewHolder.itemView.left + iconHeight + marginHorizontal,
                                    viewHolder.itemView.bottom - marginVertical
                                )
                            }
                            icon.setTint(iconTint)
                            icon.draw(c)
                        }
                    }
                }
            ).attachToRecyclerView(it.memosRecyclerView)
        }

        childFragmentManager.setFragmentResultListener(AddBottomSheetDialogFragment.REQUEST_KEY, viewLifecycleOwner) { _, result ->
            val item = result.getParcelable<MemoItem>(AddBottomSheetDialogFragment.BUNDLE_KEY) ?: return@setFragmentResultListener
            viewModel.addMemoItem(item)
        }
    }
}