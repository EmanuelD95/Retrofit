package com.emanuel.retrofit.request

import com.google.gson.annotations.SerializedName

class NewUserCreatedRequest (
    @SerializedName("name") val name: String?,
    @SerializedName("job") val job: String?
)
