package com.emanuel.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users/4")
    fun getUser(): Call<DataUser>
}