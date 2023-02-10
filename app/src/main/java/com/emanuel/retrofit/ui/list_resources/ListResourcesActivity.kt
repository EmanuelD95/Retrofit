package com.emanuel.retrofit.ui.list_resources

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.retrofit.databinding.ActivityListResourcesBinding
import com.emanuel.retrofit.model.Resource
import com.emanuel.retrofit.response.DataListResourcesResponse
import com.emanuel.retrofit.response.ResourceResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.list_resources.adapter.ResourcesAdapter
import com.emanuel.retrofit.ui.single_resources.SingleResourceActivity
import com.emanuel.retrofit.ui.single_user.SingleUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListResourcesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListResourcesBinding
    var listResources: List<Resource> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListResourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getListResources().enqueue(object: Callback<DataListResourcesResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<DataListResourcesResponse>, response: Response<DataListResourcesResponse>) {
                val data: DataListResourcesResponse? = response.body()


                listResources = ResourceResponse.toListResources(data?.listResourcesResponse!!)
                setupRecyclerView()
            }

            override fun onFailure(call: Call<DataListResourcesResponse>, t: Throwable) {
                Toast.makeText(this@ListResourcesActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun setupRecyclerView() {
        binding.rvListResources.layoutManager = LinearLayoutManager(this)
        binding.rvListResources.adapter = ResourcesAdapter(this, listResources) { resource -> onItemClicked(resource) }
    }

    private fun onItemClicked(resource: Resource) {

        val id: Int = resource.id

        val intent = Intent( this, SingleResourceActivity::class.java)
        intent.putExtra("BUNDLE_ID_RESOURCE", id)
        startActivity(intent)
    }
}