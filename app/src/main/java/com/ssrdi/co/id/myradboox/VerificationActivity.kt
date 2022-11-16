package com.ssrdi.co.id.myradboox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.ssrdi.co.id.myradboox.api.Api
import com.ssrdi.co.id.myradboox.api.RetrofitClient
import com.ssrdi.co.id.myradboox.model.VerificationResponse
import com.ssrdi.co.id.myradboox.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_verification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var codeOtp: EditText


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        btn_verification.setOnClickListener {
            codeOtp = findViewById(R.id.ed_verification)


//            val codeOtpok = ed_verification.text.toString().trim()
            val tokenFromLogin = intent.getStringExtra("token_login")
            if (codeOtp.text.isEmpty()) {
                ed_verification.error = "Kode OTP Wajib Diisi"
                ed_verification.requestFocus()
                //return@setOnClickListener
            }
            val retro = RetrofitClient().getRetrofitClientInstance().create(Api::class.java)
            retro.loginVerification(codeOtp.text.toString().toInt(), "Bearer" + tokenFromLogin)
                .enqueue(object : Callback<VerificationResponse> {
                    override fun onResponse(
                        call: Call<VerificationResponse>,
                        response: Response<VerificationResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginVerif = response.body()
                            val loginStatusResponse = loginVerif?.status.toString()
                            val loginTokenResponse = loginVerif?.token.toString()
                            if (loginStatusResponse == "success") {

                                SharedPrefManager.getInstance(applicationContext)
                                    .saveToken(loginVerif?.token.toString())

                                val intent =
                                    Intent(this@VerificationActivity, ResellerActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                startActivity(intent)


                            } else {
                                Log.e("tag", "error gak sukses di login")
                            }

                        } else {
                            Log.e("tag", "error gak sukses")
                        }
                    }
                    // batas fungsi


                    override fun onFailure(call: Call<VerificationResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }
}


