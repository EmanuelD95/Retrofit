package com.emanuel.retrofit.users

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.emanuel.retrofit.ApiService
import com.emanuel.retrofit.DataUserResponse
import com.emanuel.retrofit.UserResponse
import com.emanuel.retrofit.databinding.ActivityUsersBinding
import com.emanuel.retrofit.model.User
import com.emanuel.retrofit.single_user.SingleUserActivity
import com.emanuel.retrofit.single_user.adapter.UserAdapter
import com.emanuel.retrofit.single_user.adapter.UserProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupContact()

    }
    private fun setupContact() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = UserAdapter(UserProvider.userList) { user -> onItemSelected(user) }
    }

    private fun onItemSelected(user: User){
        //Toast.makeText(this, user.id.toString(), Toast.LENGTH_SHORT).show()

        val intent = Intent( this, SingleUserActivity::class.java)
        startActivity(intent)
    }
}