package com.example.imsihyun.android_server_hapdong

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imsihyun.android_server_hapdong.post.SigninResponse
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        var networkService: NetworkService

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance.networkService

        val user_id = RequestBody.create(MediaType.parse("signin"), login_id_edi.text.toString())
        val user_pwd = RequestBody.create(MediaType.parse("signin"), login_pw_edi.text.toString())
        val postSigninResponse = networkService.postSignin(user_id, user_pwd)

        postSigninResponse.enqueue(object : Callback<SigninResponse> {
            override fun onFailure(call: Call<SigninResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<SigninResponse>?, response: Response<SigninResponse>?) {
                login_check_btn.setOnClickListener {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }

        })


        login_signup_btn.setOnClickListener {
            val nextIntent = Intent(this, SignUpActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
