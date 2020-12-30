package haonan.tech.experment4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import haonan.tech.experment4.R
import haonan.tech.experment4.entity.Word


class WordAdapter(val WordList: LiveData<List<Word>>): RecyclerView.Adapter<WordAdapter.MyViewHolder>() {

    var allWords :List<Word> = ArrayList<Word>()

    fun setAllWords( abc : ArrayList<Word>){
        allWords =  abc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView:View = layoutInflater.inflate(R.layout.cell_card_student, parent, false)
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
        var textViewNumber:TextView = itemView.findViewById(R.id.textView_course_sno)
        var textViewEnglish:TextView = itemView.findViewById(R.id.textView_student_sname)
        var textViewChinese:TextView = itemView.findViewById(R.id.textView_student_ssex)
    }
}