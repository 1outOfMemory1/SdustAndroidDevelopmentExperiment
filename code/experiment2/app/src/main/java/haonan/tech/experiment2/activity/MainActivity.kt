package haonan.tech.experiment2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import haonan.tech.experiment2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewBtn.setOnClickListener {
            var listViewIntent = Intent(this, ListViewActivity::class.java)
            startActivity(listViewIntent)
        }


    }
}