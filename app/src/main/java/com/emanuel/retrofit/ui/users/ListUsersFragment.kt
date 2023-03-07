package com.emanuel.retrofit.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanuel.retrofit.R
import com.emanuel.retrofit.databinding.FragmentListUsersBinding
import com.emanuel.retrofit.model.User
import com.emanuel.retrofit.response.DataListUsersResponse
import com.emanuel.retrofit.response.UserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.NewUserFragment
import com.emanuel.retrofit.ui.users.adapter.UserAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUsersFragment : Fragment() {

    private lateinit var binding: FragmentListUsersBinding
    var listUser: List<User> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        binding.fabAdd.setOnClickListener {
            replaceFragment(NewUserFragment())
        }
    }

    private fun getData() {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.getListUsers().enqueue(object : Callback<DataListUsersResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<DataListUsersResponse>, response: Response<DataListUsersResponse>) {
                val data: DataListUsersResponse? = response.body()

                listUser = UserResponse.toListUsers(data?.listUsersResponse!!)
                setupRecyclerView()
            }

            override fun onFailure(call: Call<DataListUsersResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(context)
        binding.rvUser.adapter = UserAdapter(listUser) { user -> onItemClicked(user) }
    }

    private fun onItemClicked(user: User) {
        val id: Int = user.id
        val fragment = SingleUserFragment.newInstance(id)
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }
}

