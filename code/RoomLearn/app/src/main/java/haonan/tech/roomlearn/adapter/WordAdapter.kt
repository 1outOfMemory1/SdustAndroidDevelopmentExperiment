package haonan.tech.roomlearn.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import haonan.tech.roomlearn.R
import haonan.tech.roomlearn.entity.Word
import kotlinx.android.synthetic.main.cell_normal.view.*

/*
 inner class PhoneAdapter(val phoneList: List<Phone>): RecyclerView.Adapter<PhoneAdapter.ViewHolder>(){
        // 拿到资源id 方便下边函数调用
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val phoneTitle: TextView = view.findViewById(R.id.phoneTitle)
            val phoneDetail: TextView = view.findViewById(R.id.phoneDetail)
            val phoneImage:ImageView = view.findViewById(R.id.phoneImage)
        }
        // 做判断 如果是手机 就开启新activity 否则就重新渲染数据
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.phone_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val phone = phoneList[holder.absoluteAdapterPosition]
                if(isTwoPane){
                    // 双页模式
                    val fragment = phoneContentFrag as PhoneContentFragment
                    fragment.refresh(phone.phoneName, phone.phoneDetail,phone.imageid.toString())
                }else{
                    PhoneContentActivity.actionStart(parent.context, phone.phoneName, phone.phoneDetail,phone.imageid)
                }
            }
            return holder
        }
        // 绑定数据 渲染数据
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val phone = phoneList[position]
            holder.phoneTitle.text = phone.phoneName
            holder.phoneDetail.text = phone.phoneDetail
            holder.phoneImage.setImageResource(phone.imageid)
        }
        override fun getItemCount(): Int {
            return phoneList.size
        }
    }


* */


class WordAdapter(val WordList: LiveData<List<Word>>): RecyclerView.Adapter<WordAdapter.MyViewHolder>() {

    var allWords :List<Word> = ArrayList<Word>()

    fun setAllWords( abc : ArrayList<Word>){
        allWords =  abc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView:View = layoutInflater.inflate(R.layout.cell_normal, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word: Word = allWords.get(position)
        holder.textViewNumber.text = word.id.toString()
        holder.textViewChinese.text = word.chineseMeaning
        holder.textViewEnglish.text = word.word
    }



    override fun getItemCount(): Int {
        return  allWords.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textViewNumber:TextView = itemView.findViewById(R.id.textView_number)
        var textViewEnglish:TextView = itemView.findViewById(R.id.textView_englishWord)
        var textViewChinese:TextView = itemView.findViewById(R.id.textView_chineseWord)
    }
}