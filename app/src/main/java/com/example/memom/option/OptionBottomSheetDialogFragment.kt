package com.example.memom.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.memom.R
import com.example.memom.databinding.OptionBottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OptionBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: OptionViewModel by viewModels()
    private lateinit var binding: OptionBottomSheetDialogFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.option_bottom_sheet_dialog_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    companion object {

        fun show(fragmentManager: FragmentManager) = OptionBottomSheetDialogFragment()
            .show(fragmentManager, OptionBottomSheetDialogFragment::class.java.simpleName)
    }
}