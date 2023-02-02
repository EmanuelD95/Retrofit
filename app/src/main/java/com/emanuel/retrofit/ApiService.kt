package com.emanuel.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users/6")
    fun getUser(): Call<DataUserResponse>
}