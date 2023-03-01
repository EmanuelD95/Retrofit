package com.emanuel.retrofit.response

import com.google.gson.annotations.SerializedName

class LoginUserResponse(
    @SerializedName("token") val token: String?
)