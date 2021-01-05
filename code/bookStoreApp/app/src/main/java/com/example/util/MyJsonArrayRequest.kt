package com.example.util;

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MyJsonArrayRequest {

    fun abc(){
        val jsonBody = JSONObject()
        jsonBody.put("email","a")
        jsonBody.put("password", "b")
        val requestBody = jsonBody.toString()

        val registerRequest = object : StringRequest(
            Method.POST,
            "URL_REGISTER",
            Response.Listener { response ->


            },
            Response.ErrorListener { error ->

            }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }
    }
}


