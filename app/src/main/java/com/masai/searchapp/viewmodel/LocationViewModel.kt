package com.masai.searchapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.masai.searchapp.models.remote.ResponseDTO
import com.masai.searchapp.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel (private val dataRepository: LocationRepository) : ViewModel() {
    fun getLocationData(stringQuery: String, city: String) {
         viewModelScope.launch(Dispatchers.IO) {
           val result= dataRepository.getData(stringQuery, city)
        }

    }


    val user: LiveData<ResponseDTO>
        get() = dataRepository.user


}