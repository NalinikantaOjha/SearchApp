package com.masai.searchapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masai.searchapp.models.remote.ResponseDTO
import com.masai.searchapp.models.remote.api.ApiService


class LocationRepository(private val userApi:ApiService) {
    private val contentType="application/json"
    private val userLiveData = MutableLiveData<ResponseDTO>()
    val user: LiveData<ResponseDTO>
        get() = userLiveData
    suspend fun getData(queryString: String, city:String) {
        val result = userApi.getLocationFromAPI(contentType,queryString =queryString,city = city)
        if (result.body() != null) {
            userLiveData.postValue(result.body())

        }
    }

}