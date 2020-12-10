package haonan.tech.experiment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_answer.*

class Answer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        var Temp:String? = intent.getStringExtra("answer")
        answerText.text = Temp

        returnButton.setOnClickListener {
            this.finish()
        }
    }
}