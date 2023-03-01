package com.emanuel.retrofit.response

import com.google.gson.annotations.SerializedName

class NewUserResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("token") val token: String?
)