package com.emanuel.retrofit

import com.google.gson.annotations.SerializedName

data class DataUserResponse (
    @SerializedName("data") val user: UserResponse?
    //@SerializedName("support") val support: Support
)




