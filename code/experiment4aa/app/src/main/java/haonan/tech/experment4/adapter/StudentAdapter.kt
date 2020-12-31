package haonan.tech.experment4.adapter

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import haonan.tech.experment4.R
import haonan.tech.experment4.activity.CourseActivity
import haonan.tech.experment4.entity.Student
import haonan.tech.experment4.viewModel.StudentVIewModel


class StudentAdapter(val application: Application, val StudentList: LiveData<List<Student>>): RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    var allStudents :List<Student> = ArrayList<Student>()
    private var studentViewModel: StudentVIewModel =
        ViewModelProvider.AndroidViewModelFactory(application).create(StudentVIewModel::class.java)
    fun setAllStudents( abc : ArrayList<Student>){
        allStudents =  abc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView:View = layoutInflater.inflate(R.layout.cell_card_student, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student: Student = allStudents.get(position)
        holder.textViewSno.text = student.sno.toString()
        holder.textViewSname.text = student.sname
        holder.textViewSsex.text = student.ssex
        holder.textViewSage.text = student.sage.toString()
        val thisContext = holder.itemView.context
        holder.itemView.setOnClickListener {
            CourseActivity.actionStart(thisContext,student.sno!!,student.sname!!,student.ssex!!,student.sage!! )
        }

        holder.modify_student_btn.setOnClickListener {
            Toast.makeText(thisContext, student.sno.toString(),Toast.LENGTH_SHORT).show()
            val dialogView: View = LayoutInflater.from(thisContext).inflate(R.layout.add_student_dialog, null)
            val snameEditText = dialogView.findViewById<EditText>(R.id.snameEditText)
            val ssexEditText = dialogView.findViewById<EditText>(R.id.ssexEditText)
            val sageEditText = dialogView.findViewById<EditText>(R.id.sageEditText)
            snameEditText.setText(student.sname.toString())
            ssexEditText.setText(student.ssex.toString())
            sageEditText.setText(student.sage.toString())
            AlertDialog.Builder(thisContext).setView(dialogView).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("修改学生信息")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    val studentUpdateTemp = Student(student.sno,snameEditText.text.toString(),ssexEditText.text.toString(),sageEditText.text.toString().toInt())
                    studentViewModel.updateStudents(studentUpdateTemp)
//                    studentViewModel.deleteAllStudents()
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }
        holder.delete_student_btn.setOnClickListener {
            AlertDialog.Builder(thisContext).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("删除确认")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    studentViewModel.deleteStudentBySno(student.sno!!)
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }

    }


    override fun getItemCount(): Int {
        return  allStudents.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textViewSno:TextView = itemView.findViewById(R.id.textView_course_sno)
        var textViewSname:TextView = itemView.findViewById(R.id.textView_student_sname)
        var textViewSsex:TextView = itemView.findViewById(R.id.textView_student_ssex)
        var textViewSage:TextView = itemView.findViewById(R.id.textView_student_sage)
        var modify_student_btn:FloatingActionButton = itemView.findViewById(R.id.modify_student_btn)
        var delete_student_btn:FloatingActionButton = itemView.findViewById(R.id.delete_student_btn)
    }
}