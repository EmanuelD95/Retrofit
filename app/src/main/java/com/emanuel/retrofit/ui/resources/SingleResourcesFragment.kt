package com.emanuel.retrofit.ui.resources

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emanuel.retrofit.databinding.FragmentSingleResourcesBinding
import com.emanuel.retrofit.response.DataResourceResponse
import com.emanuel.retrofit.response.ResourceResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleResourcesFragment : Fragment() {

    private lateinit var binding: FragmentSingleResourcesBinding
    private var resourceId = 1

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = SingleResourcesFragment().apply {
            arguments = Bundle().apply {
                putInt("BUNDLE_ID_RESOURCE", id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSingleResourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getResources(resourceId).enqueue(object : Callback<DataResourceResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<DataResourceResponse>,
                response: Response<DataResourceResponse>
            ) {
                val data: DataResourceResponse? = response.body()
                val resource: ResourceResponse? = data?.resource

                binding.tvName.text = resource?.name
                binding.tvId.text = "ID: ${resource?.id}"
                binding.tvYeas.text = resource?.year.toString()
                binding.tvValue.text = "Value: ${resource?.value}"
                binding.vColor.setBackgroundColor(Color.parseColor(resource?.color))
            }

            override fun onFailure(call: Call<DataResourceResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt("BUNDLE_ID_RESOURCE")?.let {
            resourceId = it
        }
    }

}