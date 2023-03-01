package com.emanuel.retrofit.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.emanuel.retrofit.databinding.ActivityLoginBinding
import com.emanuel.retrofit.request.UserRequest
import com.emanuel.retrofit.response.LoginUserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.StartActivity
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
    }
    private fun loginUser(user: UserRequest) {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.loginUser(user).enqueue(object : Callback<LoginUserResponse> {
            override fun onResponse(call: Call<LoginUserResponse>, response: Response<LoginUserResponse>) {
                val data: LoginUserResponse? = response.body()
                Toast.makeText(this@LoginActivity, "${data?.token}", Toast.LENGTH_LONG).show()

                val intent = Intent(this@LoginActivity, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun validateLogin(email: String, password: String): Boolean{
        if (!email.contains("@")) return false
        if (password.length < 6) return false

        return true
    }

    override fun onClick(v: View?) {
        when (v){
            binding.btnLogin -> {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                if (validateLogin(email, password)) {
                    val user = UserRequest(email, password)
                    loginUser(user)
                } else {
                    Toast.makeText(this, "Ingrese datos correctos", Toast.LENGTH_LONG).show()
                }
            }
            binding.tvRegister -> {
                val intent = Intent( this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

    }
}