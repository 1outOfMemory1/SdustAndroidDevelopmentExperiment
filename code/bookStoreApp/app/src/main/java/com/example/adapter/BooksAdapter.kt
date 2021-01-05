package com.example.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.activity.BookDetailActivity
import com.example.entity.Book
import com.example.test.R
import kotlinx.android.synthetic.main.book_cell.view.*

class BooksAdapter : ListAdapter<Book, BooksAdapter.MyViewHolder>(DIFFCALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_cell,parent,false))
        val thisContext = holder.itemView.context

        val itemListener = holder.itemView.setOnClickListener {
            Toast.makeText(thisContext,"bookcell被点击",Toast.LENGTH_SHORT).show()
            BookDetailActivity.actionStart(parent.context,getItem(holder.adapterPosition))
        }

        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView)
                .load(getItem(position).bookCoverUrl)
                .placeholder(R.drawable.books_pic)
                .listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(holder.itemView.bookCover)
        holder.bookName.text = getItem(position).bookName
        holder.bookPrice.text = getItem(position).bookPrice
    }

    object DIFFCALLBACK: DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.bookId == newItem.bookId
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bookName: TextView = itemView.findViewById<TextView>(R.id.book_name)
        val bookPrice: TextView = itemView.findViewById<TextView>(R.id.book_price)
    }


}

