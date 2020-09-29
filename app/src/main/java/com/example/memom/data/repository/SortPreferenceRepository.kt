package com.example.memom.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SortPreferenceRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val _sortPreferenceLiveData = MutableLiveData<SortType>(getSortPreference())
    val sortPreferenceLiveData: LiveData<SortType> = _sortPreferenceLiveData.distinctUntilChanged()

    fun getSortPreference(): SortType = SortType.values()[sharedPreferences.getInt(SORT_SETTING_KEY, SortType.DateDescending.ordinal)]

    fun setSortPreference(sortType: SortType) {
        sharedPreferences.edit {
            putInt(SORT_SETTING_KEY, sortType.ordinal)
        }
        _sortPreferenceLiveData.value = sortType
    }

    companion object {
        private const val SORT_SETTING_KEY = "sort_setting_key"
    }
}

enum class SortType {
    DateDescending,
    DateAscending
}