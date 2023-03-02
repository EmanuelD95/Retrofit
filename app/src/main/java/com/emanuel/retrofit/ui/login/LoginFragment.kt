package com.emanuel.retrofit.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emanuel.retrofit.R
import com.emanuel.retrofit.databinding.FragmentLoginBinding
import com.emanuel.retrofit.request.UserRequest
import com.emanuel.retrofit.response.LoginUserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(), View.OnClickListener  {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
    }

    private fun loginUser(user: UserRequest) {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.loginUser(user).enqueue(object : Callback<LoginUserResponse> {
            override fun onResponse(call: Call<LoginUserResponse>, response: Response<LoginUserResponse>) {
                val data: LoginUserResponse? = response.body()
                Toast.makeText(context, "${data?.token}", Toast.LENGTH_LONG).show()

                val intent = Intent(context, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun validateLogin(email: String, password: String): Boolean{
        if (!email.contains("@")) return false
        if (password.length < 6) return false

        return true
    }

    override fun onClick(v: View?) {
        when (v){
            binding.btnLogin -> {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                if (validateLogin(email, password)) {
                    val user = UserRequest(email, password)
                    loginUser(user)
                } else {
                    Toast.makeText(context, "Ingrese datos correctos", Toast.LENGTH_LONG).show()
                }
            }
            binding.tvRegister -> {
                replaceFragment(RegisterFragment())
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}