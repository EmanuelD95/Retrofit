package com.emanuel.retrofit.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emanuel.retrofit.databinding.ActivityLoginBinding
import com.emanuel.retrofit.ui.StartActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v){
            binding.btnLogin -> {
                val intent = Intent( this, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            binding.tvRegister -> {
                val intent = Intent( this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }


    }
}