package com.emanuel.retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.emanuel.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.getUser().enqueue(object: Callback<DataUser> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                val data: DataUser? = response.body()
                val user: User? = data?.user

                binding.tvName.text = "${user?.firstName} ${user?.lastName}"
                binding.tvCorreo.text = user?.email
                binding.tvId.text = "ID: ${user?.id.toString()}"

                Glide.with(this@MainActivity)
                    .load(user?.avatar)
                    .into(binding.ivAvatar)
            }

            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}