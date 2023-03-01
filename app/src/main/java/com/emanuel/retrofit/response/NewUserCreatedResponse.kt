package com.emanuel.retrofit.response

import com.google.gson.annotations.SerializedName

class NewUserCreatedResponse (
    @SerializedName("name") val name: String?,
    @SerializedName("job") val job: String,
    @SerializedName("id") val id: Int?,
    @SerializedName("createdAt") val createdAt: String?
)