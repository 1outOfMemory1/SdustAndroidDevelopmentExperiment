package com.example.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.BookTypeAdapter
import com.example.adapter.BooksAdapter
import com.example.viewModel.BooksViewModel
import com.example.test.R
import kotlinx.android.synthetic.main.books_fragment.*

class BooksFragment : Fragment() {


    private lateinit var viewModel:BooksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.books_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val booksAdapter = BooksAdapter()
        books_recycler.adapter =  booksAdapter // 注意区别！！！！！  bookTypeAdapter 和 booksAdapter
        books_recycler.layoutManager = LinearLayoutManager(requireContext())

        val bookTypeAdapter = BookTypeAdapter()
        book_header_label_recycler.adapter = bookTypeAdapter
        book_header_label_recycler.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(BooksViewModel::class.java)

        // 监听是否有种类切换 如果有的话 发送网络请求 解析出具体的书
        bookTypeAdapter.labelString.observe(viewLifecycleOwner, Observer {
            viewModel.fetchBookListData(it)
        })
        // 监听是否有书籍发生变动 如果出现改变那么直接更新视图
        viewModel.bookListLive.observe(viewLifecycleOwner, Observer {
            booksAdapter.submitList(it)
        })
        // 监听是否有书籍种类发生变化 如果出现改变那么直接更新视图
        viewModel.bookTypeListLive.observe(viewLifecycleOwner, Observer {
            bookTypeAdapter.submitList(it)
        })

    }
}