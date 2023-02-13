package com.emanuel.retrofit.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emanuel.retrofit.databinding.ActivityRegisterBinding
import com.emanuel.retrofit.ui.StartActivity
import com.emanuel.retrofit.ui.list_users.ListUsersActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v){
            binding.btnRegister -> {
                val intent = Intent( this, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            binding.tvLogin -> {
                finish()
            }
        }
    }
}