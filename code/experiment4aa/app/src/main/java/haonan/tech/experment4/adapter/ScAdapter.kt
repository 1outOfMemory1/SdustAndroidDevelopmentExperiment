package haonan.tech.experment4.adapter

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import haonan.tech.experment4.R
import haonan.tech.experment4.entity.Sc
import haonan.tech.experment4.viewModel.ScVIewModel


class ScAdapter(val application: Application, val ScList: LiveData<List<Sc>>): RecyclerView.Adapter<ScAdapter.MyViewHolder>() {

    private var allScs :List<Sc> = ArrayList<Sc>()
    private var scViewModel: ScVIewModel =
        ViewModelProvider.AndroidViewModelFactory(application).create(ScVIewModel::class.java)
    fun setAllScs( abc : ArrayList<Sc>){
        allScs =  abc
    }
    fun getAllScs():List<Sc>{
        return allScs
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView:View = layoutInflater.inflate(R.layout.cell_card_course, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sc: Sc = allScs.get(position)

        val thisContext = holder.itemView.context
        val sno = sc.sno
        holder.textView_course_card_sno.text = sno.toString()
        holder.textView_course_cname.text = sc.cname
        holder.textView_course_grade.text = sc.grade.toString()
        holder.modify_course_btn.setOnClickListener {
            val dialogView: View = LayoutInflater.from(thisContext).inflate(R.layout.add_course_dialog, null)

            val cnameEditText:EditText = dialogView.findViewById(R.id.cnameEditText)
            val gradeEditText:EditText = dialogView.findViewById(R.id.gradeaaaEditText)
            cnameEditText.setText(sc.cname)
            gradeEditText.setText(sc.grade.toString())
            AlertDialog.Builder(thisContext).setView(dialogView).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("修改课程信息")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    val scTemp:Sc = Sc(sno,sno,cnameEditText.text.toString(),gradeEditText.text.toString().toInt())
                    scViewModel.updateScs(scTemp)
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }
        holder.delete_course_btn.setOnClickListener {
            AlertDialog.Builder(thisContext).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("删除确认")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }


    }


    override fun getItemCount(): Int {
        return  allScs.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView_course_card_sno = itemView.findViewById<TextView>(R.id.textView_course_card_sno)
        val textView_course_grade = itemView.findViewById<TextView>(R.id.textView_course_grade)
        val textView_course_cname = itemView.findViewById<TextView>(R.id.textView_course_cname)
        val modify_course_btn = itemView.findViewById<FloatingActionButton>(R.id.modify_course_btn)
        val delete_course_btn = itemView.findViewById<FloatingActionButton>(R.id.delete_course_btn)
        
    }
}