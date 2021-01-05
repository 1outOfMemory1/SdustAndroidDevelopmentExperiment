package com.example.util

import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import java.io.UnsupportedEncodingException


class MyStringRequest(method:Int, url:String,
                      listener:Response.Listener<String>,
                      errorListener: Response.ErrorListener):
        StringRequest(method,url,listener,errorListener) {
    override fun parseNetworkResponse(response: NetworkResponse?): Response<String> {
        var str: String? = null
        try {
            str = String(response!!.data, charset("utf-8") )
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response))
    }
}