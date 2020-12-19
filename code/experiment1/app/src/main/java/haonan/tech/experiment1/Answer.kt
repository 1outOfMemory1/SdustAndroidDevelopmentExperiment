package haonan.tech.experiment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_answer.*

class Answer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        // 获取mainActivity中传过来的答案数据
        var Temp:String? = intent.getStringExtra("answer")
        answerText.text = Temp  // 展示答案
        // 监听返回按钮的点击事件 如果点击了就结束当前activity
        returnButton.setOnClickListener {
            this.finish()
        }
    }
}