package com.emanuel.retrofit

import com.emanuel.retrofit.model.DataListUsers
import com.emanuel.retrofit.model.User
import com.google.gson.annotations.SerializedName

class DataListUsersResponse (
    @SerializedName("data") val listUsersResponse: List<UserResponse>?) {
    companion object {
        fun toDataListUsers(response: DataListUsersResponse): DataListUsers {
            return DataListUsers(
                listUsers = response.listUsersResponse.let { UserResponse.toListUser(it!!) }
            )
        }
    }
}




