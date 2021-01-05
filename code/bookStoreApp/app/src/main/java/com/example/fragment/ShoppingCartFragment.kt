package com.example.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.adapter.BooksAdapter
import com.example.adapter.ShoppingCartAdapter
import com.example.entity.Book
import com.example.entity.ResultMessage
import com.example.entity.UserShoppingCartOrOrder
import com.example.test.R
import com.example.util.GetConfig
import com.example.util.VolleySingleton
import com.example.viewModel.BooksViewModel
import com.example.viewModel.ShoppingCartViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.books_fragment.*
import kotlinx.android.synthetic.main.shopping_cart_fragment.*
import org.json.JSONArray
import org.json.JSONObject

class ShoppingCartFragment : Fragment() {

    private lateinit var baseUrl:String

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }



    private lateinit var viewModel: ShoppingCartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.shopping_cart_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val jsonObject: JSONObject = GetConfig(requireContext().assets).getJsonConfig()
        baseUrl = jsonObject["baseUrl"] as String

        val shoppingCartAdapter = ShoppingCartAdapter()
        shopping_cart_recycler.adapter =  shoppingCartAdapter // 注意区别！！！！！  bookTypeAdapter 和 booksAdapter
        shopping_cart_recycler.layoutManager = LinearLayoutManager(requireContext())

        // 主要进行总价的计算
        shoppingCartAdapter.checkedMapInShoppingCart.observe(viewLifecycleOwner, Observer {
            var tempDouble :Double = 0.0
            it.forEach{ tempDouble += it.value.bookPrice}
            // 保留两位小数
            shopping_cart_fra_totalPrice.setText(String.format("%.2f", tempDouble))
        })


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(ShoppingCartViewModel::class.java)
        // 视图更新
        viewModel.shoppingCartBookListLive.observe(viewLifecycleOwner, Observer {
            shoppingCartAdapter.submitList(it)
        })
        // 删除按钮点击监听
        shopping_cart_fra_clearAll_btn.setOnClickListener {
            if(shoppingCartAdapter.checkedMapInShoppingCart.value!=null){
                viewModel.deleteFromShopping(shoppingCartAdapter.checkedMapInShoppingCart.value!!)
                Toast.makeText(requireContext(),"删除选中成功",Toast.LENGTH_SHORT).show()
            }
        }
        // 结算按钮点击
        shopping_cart_fra_addToMine.setOnClickListener {
            if(shoppingCartAdapter.checkedMapInShoppingCart.value!=null) {
                viewModel.addToMine(shoppingCartAdapter.checkedMapInShoppingCart.value!!)
                Toast.makeText(requireContext(), "结算成功", Toast.LENGTH_SHORT).show()
            }
        }
    }





}