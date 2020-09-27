package com.example.memom.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.memom.R
import com.example.memom.databinding.AddBottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: AddViewModel by viewModels()
    private lateinit var binding: AddBottomSheetDialogFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_bottom_sheet_dialog_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.addEditText.requestFocus()
        context?.getSystemService(InputMethodManager::class.java)?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        viewModel.addItem.observe(viewLifecycleOwner) {
            setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to it))
            dismiss()
        }
    }

    companion object {

        const val REQUEST_KEY = "AddBottomSheetDialogFragmentRequest"
        const val BUNDLE_KEY = "AddBottomSheetDialogFragmentBundle"

        fun show(fragmentManager: FragmentManager) = AddBottomSheetDialogFragment()
            .show(fragmentManager, AddBottomSheetDialogFragment::class.java.simpleName)
    }
}