package com.emanuel.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emanuel.retrofit.databinding.ActivityNewUserBinding
import com.emanuel.retrofit.request.NewUserCreatedRequest
import com.emanuel.retrofit.response.NewUserCreatedResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val job = binding.etJob.text.toString()

            val newUser = NewUserCreatedRequest(name, job)
            createUser(newUser)
        }
    }

    private fun createUser(newUser: NewUserCreatedRequest) {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.createUser(newUser).enqueue(object : Callback<NewUserCreatedResponse> {
            override fun onResponse(call: Call<NewUserCreatedResponse>, response: Response<NewUserCreatedResponse>) {

                val data: NewUserCreatedResponse? = response.body()
                Toast.makeText(this@NewUserActivity, "${data?.name} - ${data?.job} - ${data?.id} - ${data?.createdAt}", Toast.LENGTH_LONG).show()
                finish()
            }

            override fun onFailure(call: Call<NewUserCreatedResponse>, t: Throwable) {
                Toast.makeText(this@NewUserActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
