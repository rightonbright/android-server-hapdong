package com.example.imsihyun.android_server_hapdong

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imsihyun.android_server_hapdong.post.SigninResponse
import com.example.imsihyun.android_server_hapdong.post.SignupResponse
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var networkService : NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance.networkService

        login_check_btn.setOnClickListener {
            postSignIn()
        }

        login_signup_btn.setOnClickListener {
            val nextIntent = Intent(this, SignUpActivity::class.java)
            startActivity(nextIntent)
        }
    }


        fun postSignIn() {
            val user_id = RequestBody.create(MediaType.parse("signin"), login_id_edi.text.toString())
            val user_pwd = RequestBody.create(MediaType.parse("signin"), login_pw_edi.text.toString())

            val postSigninResponse = networkService.postSignup(user_id, user_pwd)

            postSigninResponse.enqueue(object : Callback<SignupResponse> {
                override fun onFailure(call: Call<SignupResponse>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<SignupResponse>?, response: Response<SignupResponse>?) {
                    if(response!!.isSuccessful) {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }

                }

            })
        }

}
