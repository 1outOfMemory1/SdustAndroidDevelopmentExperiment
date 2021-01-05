package com.example.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.entity.Book
import com.example.entity.ResultMessage
import com.example.entity.UserShoppingCartOrOrder
import com.example.util.GetConfig
import com.example.util.MyStringRequest
import com.example.util.VolleySingleton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


class ShoppingCartViewModel(application: Application) : AndroidViewModel(application) {
    private val _shoppingCartBookListLive = MutableLiveData<List<Book>>()
    val shoppingCartBookListLive : LiveData<List<Book>>
        get() = _shoppingCartBookListLive


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
        Log.e("aa","$baseUrl/bookApi/getShoppingCartBooksByUsername?username=$username")
        val stringRequest = MyStringRequest(
                Request.Method.GET,
                "$baseUrl/bookApi/getShoppingCartBooksByUsername?username=$username",
                Response.Listener {
                    val tempResultMessage: ResultMessage = Gson().fromJson(it, ResultMessage::class.java)
                    if (tempResultMessage.code == 200){
                        val temp = tempResultMessage.data
                        val tempString:String = Gson().toJson(temp)
                        val bookList:List<Book> = Gson().fromJson(tempString,  object : TypeToken<List<Book>>() {}.type)
                        //Toast.makeText(thisApplication,"查询购物车成功"  , Toast.LENGTH_SHORT).show()
                        _shoppingCartBookListLive.value = bookList
                    }else{
                        Toast.makeText(thisApplication,"查询失败", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.e("aa",it.toString())
                }
        )
        VolleySingleton.getInstance(thisApplication).requestQueue.add(stringRequest)
    }

    fun deleteFromShopping(map:Map<Int, UserShoppingCartOrOrder>){
        Log.e("aa","$baseUrl/restApi/shoppingCart/delete")
        val tempList = ArrayList<UserShoppingCartOrOrder>()
        for(ele in map.values){
            tempList.add(ele)
        }
        var tempString: String = "["
        for(i in 0..tempList.size -1){
            tempString += tempList[i].toString()
            if (i!=tempList.size -1)
                tempString += ","
        }
        tempString += "]"
        if (map.isNotEmpty()){
            //val tempJsonArray: JSONArray = JSONArray(tempString)
            val okHttpClient: OkHttpClient = OkHttpClient()
            val requestBody:RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), tempString)
            val request:okhttp3.Request = okhttp3.Request.Builder().url("$baseUrl/restApi/shoppingCart/delete").post(requestBody).build()
            val call:Call = okHttpClient.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: java.io.IOException) {

                }
                override fun onResponse(call: Call, response: okhttp3.Response) {
                    //Toast.makeText(thisApplication,"删除成功aaaa"  , Toast.LENGTH_SHORT).show()
                    Log.e("aa","删除成功aaaa" +response)
                    fetchBookListData()
                }
            })
        }
    }

    fun addToMine(map:Map<Int, UserShoppingCartOrOrder>){
        val tempList = ArrayList<UserShoppingCartOrOrder>()
        for(ele in map.values){
            tempList.add(ele)
        }
        var tempString: String = "["
        for(i in 0..tempList.size -1){
            tempString += tempList[i].toString()
            if (i!=tempList.size -1)
                tempString += ","
        }
        tempString += "]"
        //val tempJsonArray: JSONArray = JSONArray(tempString)
        if (map.isNotEmpty()){
            //val tempJsonArray: JSONArray = JSONArray(tempString)
            val okHttpClient: OkHttpClient = OkHttpClient()
            val requestBody:RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), tempString)
            val request:okhttp3.Request = okhttp3.Request.Builder().url("$baseUrl/restApi/order/add").post(requestBody).build()
            val call:Call = okHttpClient.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: java.io.IOException) {

                }
                override fun onResponse(call: Call, response: okhttp3.Response) {
                    //Toast.makeText(thisApplication,"删除成功aaaa"  , Toast.LENGTH_SHORT).show()
                    Log.e("aa","插入成功aaaa" +response)
                    fetchBookListData()
                }
            })
        }

        deleteFromShopping(map)
    }
}