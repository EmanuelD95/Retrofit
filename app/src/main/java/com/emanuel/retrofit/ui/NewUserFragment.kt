package com.emanuel.retrofit.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emanuel.retrofit.R
import com.emanuel.retrofit.databinding.FragmentNewUserBinding
import com.emanuel.retrofit.databinding.FragmentSingleResourcesBinding
import com.emanuel.retrofit.request.NewUserCreatedRequest
import com.emanuel.retrofit.response.NewUserCreatedResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.resources.SingleResourcesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class NewUserFragment : Fragment() {

    private lateinit var binding: FragmentNewUserBinding
    private var resourceId = 1

    companion object {
        @JvmStatic
        fun newInstance(id: Int) = NewUserFragment().apply {
            arguments = Bundle().apply {
                putInt("BUNDLE_ID_NEW", id)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val job = binding.etJob.text.toString()

            val newUser = NewUserCreatedRequest(name, job)
            createUser(newUser)
        }
    }

    private fun createUser(newUser: NewUserCreatedRequest) {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.createUser(newUser).enqueue(object : Callback<NewUserCreatedResponse> {
            override fun onResponse(call: Call<NewUserCreatedResponse>, response: Response<NewUserCreatedResponse>) {

                val data: NewUserCreatedResponse? = response.body()
                Toast.makeText(context, "${data?.name} - ${data?.job} - ${data?.id} - ${data?.createdAt}", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<NewUserCreatedResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt("BUNDLE_ID_NEW")?.let {
            resourceId = it
        }
    }
}
