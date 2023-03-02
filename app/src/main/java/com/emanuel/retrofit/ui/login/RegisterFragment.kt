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
import com.emanuel.retrofit.databinding.FragmentRegisterBinding
import com.emanuel.retrofit.request.UserRequest
import com.emanuel.retrofit.response.NewUserResponse
import com.emanuel.retrofit.service.ApiService
import com.emanuel.retrofit.service.RetrofitHelper
import com.emanuel.retrofit.ui.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment(), View.OnClickListener  {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root    
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
    }

    private fun registerUser(newUser: UserRequest) {
        val api = RetrofitHelper.getInstance().create(ApiService::class.java)
        api.registerUser(newUser).enqueue(object: Callback<NewUserResponse> {
            override fun onResponse(call: Call<NewUserResponse>, response: Response<NewUserResponse>) {
                val data: NewUserResponse? = response.body()
                Toast.makeText(context, "${data?.id} - ${data?.token}", Toast.LENGTH_SHORT).show()

                val intent = Intent( context, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            override fun onFailure(call: Call<NewUserResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateLogin(email: String, password: String, confirmPassword: String): Boolean{
        if (!email.contains("@")) return false
        if (password.length < 6) return false
        if (confirmPassword.length < 6) return false
        if (password != confirmPassword) return false

        return true
    }
    override fun onClick(v: View?) {
        when (v){
            binding.btnRegister -> {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                val comPassword = binding.etConfirmPassword.text.toString()

                if (validateLogin(email, password, comPassword)) {
                    val newUser = UserRequest(email, password)
                    registerUser(newUser)
                } else {
                    Toast.makeText(context, "Ingrese datos correctos", Toast.LENGTH_LONG).show()
                }
            }

            binding.tvLogin -> {
                replaceFragment(LoginFragment())
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