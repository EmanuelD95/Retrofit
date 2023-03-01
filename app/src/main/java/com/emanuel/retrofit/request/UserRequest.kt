package com.emanuel.retrofit.request

import com.google.gson.annotations.SerializedName

class UserRequest (
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?
)