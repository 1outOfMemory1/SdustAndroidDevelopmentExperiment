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
        // 接受三个传过来的数据
        var nameStr = this.intent.getStringExtra("name")
        var imageIdStr = this.intent.getStringExtra("imageId")
        var phoneDetailStr = this.intent.getStringExtra("phoneDetail")
        // 分别进行赋值
        phoneName.text = nameStr
        phoneDetail.text = phoneDetailStr
        if (imageIdStr != null) {
            imageView.setImageResource(imageIdStr.toInt())
        }
    }
}