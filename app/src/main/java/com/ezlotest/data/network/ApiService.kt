package com.ezlotest.data.network

import com.ezlotest.data.network.model.DevicesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("items.test")
    suspend fun getData(): DevicesResponse
}