package com.example.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.entity.Book
import com.example.entity.ResultMessage
import com.example.util.GetConfig
import com.example.util.MyStringRequest
import com.example.util.VolleySingleton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class BooksViewModel(application: Application) : AndroidViewModel(application) {
    private var baseUrl:String
    private var thisApplication: Application = application
    private val _bookListLive = MutableLiveData<List<Book>>()
    private val _bookTypeListLive = MutableLiveData<List<String>>()
    val bookListLive : LiveData<List<Book>>
    get() = _bookListLive
    val bookTypeListLive: LiveData<List<String>>
    get() = _bookTypeListLive

    init {
        val jsonObject: JSONObject = GetConfig(thisApplication.assets).getJsonConfig()
        baseUrl = jsonObject["baseUrl"] as String
        fetchBookListData("文学")
        fetchBookTypeListData()
    }

    fun fetchBookListData(label:String){
        val stringRequest = MyStringRequest(
                Request.Method.GET,
                "$baseUrl/bookApi/getBooksByLabel?label=$label",
                Response.Listener {
                    val tempResultMessage: ResultMessage = Gson().fromJson(it, ResultMessage::class.java)
                    if (tempResultMessage.code == 200){
                        val temp = tempResultMessage.data
                        val tempString:String = Gson().toJson(temp)
                        val bookList:List<Book> =Gson().fromJson(tempString,  object : TypeToken<List<Book>>() {}.type)
                        Toast.makeText(thisApplication,"查询成功"  , Toast.LENGTH_SHORT).show()
                        _bookListLive.value = bookList
                    }else{
                        Toast.makeText(thisApplication,"查询失败", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.e("aa",it.toString())
                }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    fun fetchBookTypeListData(){
        val stringRequest = MyStringRequest(
            Request.Method.GET,
            "$baseUrl/bookApi/getAllBookType",
            Response.Listener {
                val tempResultMessage: ResultMessage = Gson().fromJson(it, ResultMessage::class.java)
                if (tempResultMessage.code == 200){
                    val temp = tempResultMessage.data
                    val tempString:String = Gson().toJson(temp)
                    val bookTypeList:List<String> =Gson().fromJson(tempString,  object : TypeToken<List<String>>() {}.type)
                    _bookTypeListLive.value = bookTypeList
                    Toast.makeText(thisApplication,"查询书籍类型成功"  , Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(thisApplication,"查询失败", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Log.e("aa",it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }
}