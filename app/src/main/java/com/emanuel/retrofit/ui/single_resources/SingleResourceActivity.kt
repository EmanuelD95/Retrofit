package com.emanuel.retrofit.ui.single_resources

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emanuel.retrofit.databinding.ActivitySingleResourceBinding
import com.emanuel.retrofit.response.DataResourceResponse
import com.emanuel.retrofit.response.ResourceResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleResourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleResourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("BUNDLE_ID_RESOURCE", 1)

        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getResources(id).enqueue(object: Callback<DataResourceResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<DataResourceResponse>, response: Response<DataResourceResponse>) {
                val data: DataResourceResponse? = response.body()
                val resource: ResourceResponse? = data?.resource

                binding.tvName.text = resource?.name
                binding.tvId.text = "ID: ${resource?.id}"
                binding.tvYeas.text = resource?.year.toString()
                binding.tvValue.text = "Value: ${resource?.value}"
                binding.vColor.setBackgroundColor(Color.parseColor(resource?.color))
            }

            override fun onFailure(call: Call<DataResourceResponse>, t: Throwable) {
                Toast.makeText(this@SingleResourceActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}

