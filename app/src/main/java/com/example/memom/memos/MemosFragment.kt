package com.example.memom.memos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        binding.viewModel = viewModel
    }
}