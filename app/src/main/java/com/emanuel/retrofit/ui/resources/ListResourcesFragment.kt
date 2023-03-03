package com.emanuel.retrofit.ui.resources

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.retrofit.R
import com.emanuel.retrofit.databinding.FragmentListResourcesBinding
import com.emanuel.retrofit.model.Resource
import com.emanuel.retrofit.response.DataListResourcesResponse
import com.emanuel.retrofit.response.ResourceResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.resources.adapter.ResourcesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListResourcesFragment : Fragment() {

    private lateinit var binding: FragmentListResourcesBinding
    var listResources: List<Resource> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListResourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun setupRecyclerView() {
        binding.rvListResources.layoutManager = LinearLayoutManager(context)
        binding.rvListResources.adapter = ResourcesAdapter(listResources) { resource -> onItemClicked(resource) }
    }

    private fun onItemClicked(resource: Resource) {
        val id: Int = resource.id
        val fragment = SingleResourcesFragment.newInstance(id)
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}