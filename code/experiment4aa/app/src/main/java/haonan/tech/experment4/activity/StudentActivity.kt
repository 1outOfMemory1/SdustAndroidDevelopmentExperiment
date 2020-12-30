package haonan.tech.experment4.activity

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import haonan.tech.experment4.R
import haonan.tech.experment4.adapter.StudentAdapter
import haonan.tech.experment4.entity.Student
import haonan.tech.experment4.viewModel.StudentVIewModel
import kotlinx.android.synthetic.main.activity_main.*



class StudentActivity : AppCompatActivity() {
    private lateinit var studentViewModel: StudentVIewModel
    private lateinit var myAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        studentViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(StudentVIewModel::class.java)
        myAdapter = StudentAdapter(application ,studentViewModel.getAllStudentsLive())
        wordRecyclerView.layoutManager = LinearLayoutManager(this)
        wordRecyclerView.adapter = myAdapter

        studentViewModel.getAllStudentsLive().observe(this, Observer {
            myAdapter.setAllStudents(it as ArrayList<Student>)
            myAdapter.notifyDataSetChanged()
        })


        add_student_btn.setOnClickListener{
            val dialogView: View = LayoutInflater.from(this).inflate(R.layout.add_student_dialog, null)
            AlertDialog.Builder(this).setView(dialogView).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("增加学生")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    val snameEditText = dialogView.findViewById<EditText>(R.id.cnameEditText)
                    val ssexEditText = dialogView.findViewById<EditText>(R.id.gradeEditText)
                    val sageEditText = dialogView.findViewById<EditText>(R.id.sageEditText)
                    //Toast.makeText(context,snameEditText.text,Toast.LENGTH_SHORT).show()
                    val studentTemp = Student(null,snameEditText.text.toString(),ssexEditText.text.toString(),sageEditText.text.toString().toInt() )
                    studentViewModel.insertStudents(studentTemp)
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }
    }
}