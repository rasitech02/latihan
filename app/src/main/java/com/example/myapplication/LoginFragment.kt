package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.api.Api
import com.example.myapplication.api.RetrofitClient
import com.example.myapplication.model.UserRequest
import com.example.myapplication.model.UserResponse
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        initAction()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

    }
    private fun initAction(){
        btn_login.setOnClickListener{
            userLogin()
        }
    }
    private fun userLogin(){

        val request = UserRequest()
        request.username = q_username.text.toString().trim()
        request.password = q_password.text.toString().trim()

        val retro = RetrofitClient().getRetrofitClientInstance().create(Api::class.java)
        retro.UserLogin(request).enqueue(object : Callback <UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                    if (user != null) {
                        Log.e("token", user.token.toString())
                        Log.e("token", user.priviledge.toString())
                    }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}