package com.example.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.example.entity.Book
import com.example.entity.ResultMessage
import com.example.util.GetConfig
import com.example.util.MyStringRequest
import com.example.util.VolleySingleton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class MineViewModel(application: Application) : AndroidViewModel(application) {
    private val _orderBookListLive = MutableLiveData<List<Book>>()
    val orderBookListLive : LiveData<List<Book>>
        get() = _orderBookListLive

    private var baseUrl:String
    private var thisApplication: Application = application
    private  var username:String
    init {
        val jsonObject: JSONObject = GetConfig(thisApplication.assets).getJsonConfig()
        baseUrl = jsonObject["baseUrl"] as String
        val prefs =  thisApplication.getSharedPreferences("currentUser", Context.MODE_PRIVATE)
        username = prefs.getString("userName","")!!
        fetchBookListData()
    }


    fun fetchBookListData(){
        Log.e("aa","$baseUrl/bookApi/getOrderBooksByUsername?username=$username")
        val stringRequest = MyStringRequest(
            Request.Method.GET,
            "$baseUrl/bookApi/getOrderBooksByUsername?username=$username",
            Response.Listener {
                val tempResultMessage: ResultMessage = Gson().fromJson(it, ResultMessage::class.java)
                if (tempResultMessage.code == 200){
                    val temp = tempResultMessage.data
                    val tempString:String = Gson().toJson(temp)
                    val bookList:List<Book> = Gson().fromJson(tempString,  object : TypeToken<List<Book>>() {}.type)
                    //Toast.makeText(thisApplication,"查询订单成功"  , Toast.LENGTH_SHORT).show()
                    _orderBookListLive.value = bookList
                }else{
                    Toast.makeText(thisApplication,"查询订单失败", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Log.e("aa",it.toString())
            }
        )
        VolleySingleton.getInstance(thisApplication).requestQueue.add(stringRequest)
    }



}