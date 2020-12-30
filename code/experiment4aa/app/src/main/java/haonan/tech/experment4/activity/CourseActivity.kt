package haonan.tech.experment4.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import haonan.tech.experment4.R
import haonan.tech.experment4.entity.Student
import kotlinx.android.synthetic.main.activity_course.*

class CourseActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, sno: Int, sname: String, ssex: String, sage: Int) {
            val courseIntent = Intent(context, CourseActivity::class.java).apply {
                putExtra("sno", sno.toString())
                putExtra("sname", sname)
                putExtra("ssex", ssex)
                putExtra("sage", sage.toString())
            }
            context.startActivity(courseIntent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        textView_course_sno.text = intent.getStringExtra("sno")
        textView_course_sname.text = intent.getStringExtra("sname")
        textView_course_ssex.text = intent.getStringExtra("ssex")
        textView_course_ssage.text = intent.getStringExtra("sage")

        add_course_btn.setOnClickListener{
            val dialogView: View = LayoutInflater.from(this).inflate(R.layout.add_course_dialog, null)
            AlertDialog.Builder(this).setView(dialogView).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("增加选课")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    val cnameEditText = dialogView.findViewById<EditText>(R.id.cnameEditText)
                    val gradeEditText = dialogView.findViewById<EditText>(R.id.gradeEditText)
                    //Toast.makeText(context,snameEditText.text,Toast.LENGTH_SHORT).show()
//                    val studentTemp = Student(null,snameEditText.text.toString(),ssexEditText.text.toString(),sageEditText.text.toString().toInt() )
//                    studentViewModel.insertStudents(studentTemp)
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }
    }
}