package com.emanuel.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<DataUserResponse>

    @GET("users")
    fun getListUsers(): Call<DataListUsersResponse>
}

