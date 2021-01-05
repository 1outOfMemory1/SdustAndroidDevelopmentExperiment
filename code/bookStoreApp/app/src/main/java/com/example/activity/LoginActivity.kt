package com.example.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.entity.ResultMessage
import com.example.entity.User
import com.example.test.databinding.ActivityLoginBinding
import com.example.util.GetConfig
import com.example.util.VolleySingleton
import com.google.gson.Gson
import org.json.JSONObject
import kotlin.math.log


class LoginActivity : AppCompatActivity() {


    private lateinit var  loginLayoutViewBinding:ActivityLoginBinding
    private lateinit var baseUrl:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        // 这个loginLayoutViewBinding 太长了 简写一下
        val binding:ActivityLoginBinding = loginLayoutViewBinding
        binding.loginBtn.setOnClickListener {
            val username =  binding.usernameEditText.text.toString()
            val password =  binding.passwordEditText.text.toString()
            if (username == "" || password == ""){
                Toast.makeText(this,"请将用户名或者密码填写完毕",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val stringRequest = StringRequest(
                Request.Method.GET,
                "$baseUrl/userApi/login?username=$username&password=$password",
                Response.Listener {
                    val tempResultMessage:ResultMessage = Gson().fromJson(it,ResultMessage::class.java)
                    if (tempResultMessage.code == 200){
                        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                        val temp = tempResultMessage.data
                        //Log.e("aa", temp)
                        val currentUser = Gson().fromJson(temp.toString(),User::class.java)
                        MainActivity.actionStart(this,currentUser)
                    }else{
                        Toast.makeText(this,"登录失败，账号或者密码错误",Toast.LENGTH_SHORT).show()
                    }

//                    Log.e("aa", currentUser.toString())
                },
                Response.ErrorListener {
                    Log.e("aa",it.toString())
                }
            )
            VolleySingleton.getInstance(application).requestQueue.add(stringRequest)
        }
        binding.registerBtn.setOnClickListener {
            val username =  binding.usernameEditText.text.toString()
            val password =  binding.passwordEditText.text.toString()
            if (username == "" || password == ""){
                Toast.makeText(this,"请将用户名或者密码填写完毕",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val stringRequest = StringRequest(
                Request.Method.GET,
                "$baseUrl/userApi/register?username=$username&password=$password",
                Response.Listener {
                    if (Gson().fromJson(it,ResultMessage::class.java).code == 200){
                        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.e("aa",it.toString())
                }
            )
            VolleySingleton.getInstance(application).requestQueue.add(stringRequest)

        }
    }

    fun init(){
        loginLayoutViewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginLayoutViewBinding.root)
        // 解析成json对象
        val jsonObject:JSONObject = GetConfig(assets).getJsonConfig()
        // 读出基地址
        baseUrl = jsonObject["baseUrl"].toString()
        Log.e("tag",baseUrl)
    }
}