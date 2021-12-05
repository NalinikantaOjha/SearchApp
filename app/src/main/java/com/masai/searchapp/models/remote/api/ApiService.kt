package com.masai.searchapp.models.remote.api

import com.masai.searchapp.models.remote.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Accept: application/json")
    @GET("autocomplete")
    suspend fun getLocationFromAPI(
        @Header("Content-type") contentType:String,
        @Query("queryString") queryString:String,
        @Query("city")city:String
    ): Response<ResponseDTO>
}