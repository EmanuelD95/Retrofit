package com.emanuel.retrofit.service

import com.emanuel.retrofit.request.NewUserCreatedRequest
import com.emanuel.retrofit.request.UserRequest
import com.emanuel.retrofit.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<DataUserResponse>

    @GET("users")
    fun getListUsers(): Call<DataListUsersResponse>

    @GET("listResources/{id}")
    fun getResources(@Path("id") id: Int): Call<DataResourceResponse>

    @GET("unknown")
    fun getListResources(): Call<DataListResourcesResponse>

    @POST("register")
    fun registerUser(@Body newUser: UserRequest): Call<NewUserResponse>

    @POST("login")
    fun loginUser(@Body user: UserRequest): Call<LoginUserResponse>

    @POST("users")
    fun createUser(@Body newUser: NewUserCreatedRequest): Call<NewUserCreatedResponse>
}


