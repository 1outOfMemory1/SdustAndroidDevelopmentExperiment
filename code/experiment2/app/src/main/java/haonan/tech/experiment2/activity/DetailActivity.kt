package haonan.tech.experiment2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import haonan.tech.experiment2.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var nameStr = this.intent.getStringExtra("name")
        var imageIdStr = this.intent.getStringExtra("imageId")
        var phoneDetailStr = this.intent.getStringExtra("phoneDetail")
        phoneName.text = nameStr
        phoneDetail.text = phoneDetailStr
        if (imageIdStr != null) {
            imageView.setImageResource(imageIdStr.toInt())
        }

    }
}