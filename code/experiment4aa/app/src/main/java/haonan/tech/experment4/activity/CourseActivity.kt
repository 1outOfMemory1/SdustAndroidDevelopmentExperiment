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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import haonan.tech.experment4.R
import haonan.tech.experment4.adapter.ScAdapter
import haonan.tech.experment4.entity.Sc
import haonan.tech.experment4.viewModel.ScVIewModel
import kotlinx.android.synthetic.main.activity_course.*

class CourseActivity : AppCompatActivity() {

    lateinit var scViewModel :ScVIewModel
    private lateinit var myAdapter: ScAdapter
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
        val sno:Int = intent.getStringExtra("sno").toString().toInt()
        textView_course_sno.text = sno.toString()
        textView_course_sname.text = intent.getStringExtra("sname")
        textView_course_ssex.text = intent.getStringExtra("ssex")
        textView_course_ssage.text = intent.getStringExtra("sage")
        scViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(ScVIewModel::class.java)
        var aa = scViewModel.getAllScsLive(sno)


        myAdapter = ScAdapter(application ,aa)
        recyclerViewOfCourse.layoutManager = LinearLayoutManager(this)
        recyclerViewOfCourse.adapter = myAdapter

        aa.observe(this, Observer {
            myAdapter.setAllScs(it as ArrayList<Sc>)
            myAdapter.notifyDataSetChanged()
        })





        add_course_btn.setOnClickListener{
            val dialogView: View = LayoutInflater.from(this).inflate(R.layout.add_course_dialog, null)
            AlertDialog.Builder(this).setView(dialogView).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("增加选课")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    val cnameEditText = dialogView.findViewById<EditText>(R.id.cnameEditText)
                    val gradeEditText = dialogView.findViewById<EditText>(R.id.gradeaaaEditText)
                    //Toast.makeText(context,snameEditText.text,Toast.LENGTH_SHORT).show()
                    val courseTemp = Sc(sno,sno, cnameEditText.text.toString(),gradeEditText.text.toString().toInt())
                    scViewModel.insertScs(courseTemp)
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
        }
    }
}