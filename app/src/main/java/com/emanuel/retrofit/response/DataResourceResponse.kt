package com.emanuel.retrofit.response

import com.google.gson.annotations.SerializedName

data class DataResourceResponse (
        @SerializedName("data") val resource: ResourceResponse?
)
