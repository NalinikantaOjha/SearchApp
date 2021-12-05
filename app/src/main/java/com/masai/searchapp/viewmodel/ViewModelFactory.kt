package com.masai.searchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.searchapp.repository.LocationRepository


class ViewModelFactory(val repo: LocationRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationViewModel(repo) as T
    }
}