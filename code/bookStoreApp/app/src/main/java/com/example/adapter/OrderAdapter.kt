package com.example.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.entity.Book
import com.example.test.R
import kotlinx.android.synthetic.main.order_book_cell.view.*
import kotlinx.android.synthetic.main.shopping_cart_book_cell.view.*

class OrderAdapter: ListAdapter<Book, OrderAdapter.MyViewHolder>(OrderAdapter.DIFFCALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderAdapter.MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.order_book_cell,parent,false))
        return holder
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book:Book = getItem(position)
        holder.order_book_cell_bookName.text = book.bookName
        holder.order_book_cell_bookPrice.text = book.bookPrice.toString()

        Glide.with(holder.itemView)
            .load(getItem(position).bookCoverUrl)
            .placeholder(R.drawable.books_pic)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .into(holder.itemView.order_book_cell_cover)
    }



    object DIFFCALLBACK: DiffUtil.ItemCallback<Book>(){
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.bookId == newItem.bookId
        }

    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val order_book_cell_cover = itemView.findViewById<ImageView>(R.id.order_book_cell_cover)
        val order_book_cell_bookName = itemView.findViewById<TextView>(R.id.order_book_cell_bookName)
        val order_book_cell_bookPrice = itemView.findViewById<TextView>(R.id.order_book_cell_bookPrice)
    }



}