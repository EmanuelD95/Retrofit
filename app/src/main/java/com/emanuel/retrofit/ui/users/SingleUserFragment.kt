package com.emanuel.retrofit.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.emanuel.retrofit.databinding.FragmentSingleUserBinding
import com.emanuel.retrofit.response.DataUserResponse
import com.emanuel.retrofit.response.UserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingleUserFragment : Fragment() {

    private lateinit var binding: FragmentSingleUserBinding
    private var userId = 1


    companion object {
        @JvmStatic
        fun newInstance(id: Int) = SingleUserFragment().apply {
            arguments = Bundle().apply {
                putInt("BUNDLE_ID_USER", id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingleUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getUser(userId).enqueue(object : Callback<DataUserResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<DataUserResponse>,
                response: Response<DataUserResponse>
            ) {
                val data: DataUserResponse? = response.body()
                val user: UserResponse? = data?.user

                binding.tvName.text = "${user?.firstName} ${user?.lastName}"
                binding.tvCorreo.text = user?.email
                binding.tvId.text = "ID: ${user?.id.toString()}"

                context?.let {
                    Glide.with(it)
                        .load(user?.avatar)
                        .into(binding.ivAvatar)
                }
            }

            override fun onFailure(call: Call<DataUserResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt("BUNDLE_ID_USER")?.let {
            userId = it
        }
    }
}

