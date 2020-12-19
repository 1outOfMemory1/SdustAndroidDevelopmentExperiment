package haonan.tech.fragmentnewsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.fastjson.JSON
import haonan.tech.fragmentnewsapp.R
import haonan.tech.fragmentnewsapp.entity.Phone
import haonan.tech.fragmentnewsapp.fragment.NewsContentFragment
import haonan.tech.fragmentnewsapp.fragment.PhoneContentFragment
import kotlinx.android.synthetic.main.activity_main_phone.*
import kotlinx.android.synthetic.main.activity_news_content.*

class PhoneContentActivity : AppCompatActivity() {
    companion object{
        // 将要用的数据封装了一下子  外人启动的时候直接调用就行  减少沟通次数 提高效率
        fun actionStart(context: Context, title: String, content: String,id:Int){
            var phoneContentIntent = Intent(context, PhoneContentActivity::class.java).apply {
                putExtra("phone_name", title)
                putExtra("phone_detail", content)
                putExtra("phoneImageId", id.toString())
            }
            context.startActivity(phoneContentIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_content)
        val phone_name = intent.getStringExtra("phone_name")
        val phone_detail = intent.getStringExtra("phone_detail")
        val phone_image_id = intent.getStringExtra("phoneImageId")
        if(phone_name != null && phone_detail!=null ){
            var fragment = phoneContentFrag as PhoneContentFragment
            fragment.refresh(phone_name, phone_detail,phone_image_id.toString())
        }
    }


}