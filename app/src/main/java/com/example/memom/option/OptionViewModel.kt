package com.example.memom.option

import androidx.hilt.lifecycle.ViewModelInject
import com.example.memom.R
import androidx.lifecycle.ViewModel
import com.example.memom.data.repository.SortPreferenceRepository
import com.example.memom.data.repository.SortType

class OptionViewModel @ViewModelInject constructor(
    private val sortPreferenceRepository: SortPreferenceRepository
) : ViewModel() {

    fun getSortRadioButtonId(): Int {
        return when (sortPreferenceRepository.getSortPreference()) {
            SortType.DateAscending -> R.id.option_sort_radio_button_date_asc
            SortType.DateDescending -> R.id.option_sort_radio_button_date_desc
        }
    }

    fun onSortByDateAscendingClicked() {
        sortPreferenceRepository.setSortPreference(SortType.DateAscending)
    }

    fun onSortByDateDescendingClicked() {
        sortPreferenceRepository.setSortPreference(SortType.DateDescending)
    }
}