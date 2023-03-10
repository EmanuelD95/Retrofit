package com.emanuel.retrofit.ui.single_user

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.emanuel.retrofit.*
import com.emanuel.retrofit.databinding.ActivitySingleUserBinding
import com.emanuel.retrofit.response.DataUserResponse
import com.emanuel.retrofit.response.UserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("BUNDLE_ID_USER", 1)

        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getUser(id).enqueue(object: Callback<DataUserResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<DataUserResponse>, response: Response<DataUserResponse>) {
                val data: DataUserResponse? = response.body()
                val user: UserResponse? = data?.user

                binding.tvName.text = "${user?.firstName} ${user?.lastName}"
                binding.tvCorreo.text = user?.email
                binding.tvId.text = "ID: ${user?.id.toString()}"

                Glide.with(this@SingleUserActivity)
                    .load(user?.avatar)
                    .into(binding.ivAvatar)
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                Toast.makeText(this@SingleUserActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}