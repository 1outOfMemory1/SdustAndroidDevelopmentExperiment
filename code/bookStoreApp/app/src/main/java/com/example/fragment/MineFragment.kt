package com.example.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.OrderAdapter
import com.example.adapter.ShoppingCartAdapter
import com.example.test.R
import com.example.viewModel.MineViewModel
import com.example.viewModel.ShoppingCartViewModel
import kotlinx.android.synthetic.main.mine_fragment.*
import kotlinx.android.synthetic.main.shopping_cart_fragment.*

class MineFragment : Fragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    private lateinit var viewModel: MineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(
            MineViewModel::class.java)
        val orderAdapter = OrderAdapter()
        mine_recycler.adapter =  orderAdapter
        mine_recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.orderBookListLive.observe(viewLifecycleOwner, Observer {
            orderAdapter.submitList(it)
        })

    }

}