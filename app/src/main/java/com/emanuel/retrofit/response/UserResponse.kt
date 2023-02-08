package com.emanuel.retrofit.response

import com.emanuel.retrofit.model.User
import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("avatar") val avatar: String?) {
    companion object {
        fun toUser(response: UserResponse): User {
            return User(
                id = response.id?:0,
                email = response.email?:"",
                firstName = response.firstName?:"",
                lastName = response.lastName?:"",
                avatar = response.avatar?:""
            )
        }

        fun toListUser(listUsersResponse: List<UserResponse>): List<User>{
            val list = ArrayList<User>()
            for(dataResponse in listUsersResponse) {
                list.add(toUser(dataResponse))
            }
            return list
        }

    }
}
