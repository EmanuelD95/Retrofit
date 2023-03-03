package com.emanuel.retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emanuel.retrofit.databinding.ActivityStartBinding
import com.emanuel.retrofit.ui.list_users.ListUsersActivity
import com.emanuel.retrofit.ui.resources.ResourceActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, ListUsersActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, ResourceActivity::class.java)
            startActivity(intent)
        }

    }
}