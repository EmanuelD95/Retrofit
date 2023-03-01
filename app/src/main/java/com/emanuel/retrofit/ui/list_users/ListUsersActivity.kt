package com.emanuel.retrofit.ui.list_users

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.retrofit.*
import com.emanuel.retrofit.databinding.ActivityUsersBinding
import com.emanuel.retrofit.model.User
import com.emanuel.retrofit.response.DataListUsersResponse
import com.emanuel.retrofit.response.UserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.NewUserActivity
import com.emanuel.retrofit.ui.single_user.SingleUserActivity
import com.emanuel.retrofit.ui.list_users.adapter.UserAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    //var userList: List<UserResponse?>?= emptyList()
    var userList: List<User> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.fabAdd.setOnClickListener { view ->
            /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()*/
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getListUsers().enqueue(object: Callback<DataListUsersResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<DataListUsersResponse>, response: Response<DataListUsersResponse>) {
                val data: DataListUsersResponse? = response.body()

                //val listUsers: List<UserResponse?>? = data?.listUsers

                userList = UserResponse.toListUsers(data?.listUsersResponse!!)
                setupRecyclerView()
            }

            override fun onFailure(call: Call<DataListUsersResponse>, t: Throwable) {
                Toast.makeText(this@ListUsersActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        //binding.rvUser.adapter = UserAdapter(UserProvider.userList) { user -> onItemSelected(user) }
        binding.rvUser.adapter = UserAdapter(userList) { user -> onItemClicked(user) }
    }

    private fun onItemClicked(user: User){
        //Toast.makeText(this, user.id.toString(), Toast.LENGTH_SHORT).show()
        val id: Int = user.id

        val intent = Intent( this, SingleUserActivity::class.java)
        intent.putExtra("BUNDLE_ID_USER", id)
        startActivity(intent)
    }

}