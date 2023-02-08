package com.emanuel.retrofit.ui.list_resources

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emanuel.retrofit.R
import com.emanuel.retrofit.databinding.ActivityListResourcesBinding
import com.emanuel.retrofit.databinding.ActivityStartBinding
import com.emanuel.retrofit.ui.StartActivity

class ListResourcesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListResourcesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListResourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}