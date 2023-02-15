package com.emanuel.retrofit.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.emanuel.retrofit.databinding.ActivityRegisterBinding
import com.emanuel.retrofit.ui.StartActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
    }
    private fun validateLogin(email: String, password: String, confirmPassword: String): Boolean{
        if (!email.contains("@")) return false
        if (password.length < 8) return false
        if (confirmPassword.length < 8)return false
        if (password != confirmPassword) return false

        return true
    }
    override fun onClick(v: View?) {
        when (v){
            binding.btnRegister -> {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val comPassword = binding.etConfirmPassword.text.toString()

                if (validateLogin(email, password, comPassword)) {
                    val intent = Intent( this, StartActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Ingrese datos correctos", Toast.LENGTH_LONG).show()
                }
            }

            binding.tvLogin -> {
                finish()
            }
        }
    }
}
