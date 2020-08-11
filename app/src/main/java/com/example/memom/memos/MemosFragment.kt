package com.example.memom.memos

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.memom.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemosFragment : Fragment(R.layout.fragment_memos) {

    private val viewModel: MemosViewModel by viewModels()
}