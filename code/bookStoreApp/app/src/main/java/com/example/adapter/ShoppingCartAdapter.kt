package com.example.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import kotlinx.android.synthetic.main.shopping_cart_book_cell.view.*

class ShoppingCartAdapter : ListAdapter<Book, ShoppingCartAdapter.MyViewHolder>(ShoppingCartAdapter.DIFFCALLBACK){

    private val _checkedBookInShoppingCart = MutableLiveData<List<Book>>()
    val checkedBookInShoppingCart : LiveData<List<Book>>
        get() = _checkedBookInShoppingCart


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shopping_cart_book_cell,parent,false))
        val thisContext = holder.itemView.context
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.shopping_cart_book_cell_chekBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(holder.itemView.context,"选择框状态改变",Toast.LENGTH_SHORT).show()
            if (isChecked){

            }
        }


        val book:Book = getItem(position)
        holder.s_book_name.text = book.bookName
        holder.s_book_price.text = book.bookPrice
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
                .into(holder.itemView.s_bookCover)
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
        val s_book_name = itemView.findViewById<TextView>(R.id.s_book_name)
        val s_book_price = itemView.findViewById<TextView>(R.id.s_book_price)
        val shopping_cart_book_cell_chekBox = itemView.findViewById<CheckBox>(R.id.shopping_cart_book_cell_chekBox)

    }


}