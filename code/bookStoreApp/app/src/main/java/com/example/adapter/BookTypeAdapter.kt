package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class BookTypeAdapter: ListAdapter<String, BookTypeAdapter.MyViewHolder>(DIFFCALLBACK) {
    private val _labelString = MutableLiveData<String>()
    val labelString : LiveData<String>
    get() = _labelString


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookTypeAdapter.MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_type_cell,parent,false))
        val thisContext = holder.itemView.context
        holder.bookTypeCellBtn.setOnClickListener {
            Toast.makeText(thisContext,holder.bookTypeCellBtn.text.toString() + "被点击",Toast.LENGTH_SHORT).show()
            _labelString.value = holder.bookTypeCellBtn.text.toString()
        }
        return holder
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bookTypeCellBtn.text = getItem(position)
    }
    object DIFFCALLBACK: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bookTypeCellBtn = itemView.findViewById<Button>(R.id.book_type_cell_btn)
    }
}