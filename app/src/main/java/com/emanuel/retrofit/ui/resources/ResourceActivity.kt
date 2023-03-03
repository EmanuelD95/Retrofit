package com.emanuel.retrofit.ui.resources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.emanuel.retrofit.R
import com.emanuel.retrofit.databinding.ActivityResourceBinding

class ResourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(ListResourcesFragment())
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}