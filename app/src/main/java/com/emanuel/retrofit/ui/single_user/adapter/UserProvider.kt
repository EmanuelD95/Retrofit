package com.emanuel.retrofit.ui.single_user.adapter

import com.emanuel.retrofit.model.User

class UserProvider {
    companion object {
        val userList = listOf<User>(
            User(1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
            User(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg")
        )
    }
}