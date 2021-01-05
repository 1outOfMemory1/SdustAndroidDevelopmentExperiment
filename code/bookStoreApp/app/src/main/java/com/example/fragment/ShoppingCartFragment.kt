package com.example.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.BooksAdapter
import com.example.adapter.ShoppingCartAdapter
import com.example.test.R
import com.example.viewModel.BooksViewModel
import com.example.viewModel.ShoppingCartViewModel
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.books_fragment.*
import kotlinx.android.synthetic.main.shopping_cart_fragment.*

class ShoppingCartFragment : Fragment() {

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
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(ShoppingCartViewModel::class.java)


        val shoppingCartAdapter = ShoppingCartAdapter()
        shopping_cart_recycler.adapter =  shoppingCartAdapter // 注意区别！！！！！  bookTypeAdapter 和 booksAdapter
        shopping_cart_recycler.layoutManager = LinearLayoutManager(requireContext())


        viewModel.shoppingCartBookListLive.observe(viewLifecycleOwner, Observer {
            shoppingCartAdapter.submitList(it)
        })

        shopping_cart_fra_clearAll_btn.setOnClickListener {
            Toast.makeText(requireContext(),"清空购物车成功",Toast.LENGTH_SHORT).show()
        }
        shopping_cart_fra_addToMine.setOnClickListener {
            Toast.makeText(requireContext(),"结算成功",Toast.LENGTH_SHORT).show()
        }
    }

}