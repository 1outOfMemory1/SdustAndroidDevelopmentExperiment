package haonan.tech.fragmentnewsapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import haonan.tech.fragmentnewsapp.R
import haonan.tech.fragmentnewsapp.fragment.NewsContentFragment
import kotlinx.android.synthetic.main.activity_news_content.*

class NewsContentActivity : AppCompatActivity() {
    companion object{
        // 将要用的数据封装了一下子  外人启动的时候直接调用就行  减少沟通次数 提高效率
        fun actionStart(context: Context, title: String, content: String){
            var newsContentIntent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(newsContentIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if(title != null && content!=null ){
            var fragment = newsContentFrag as NewsContentFragment
            fragment.refresh(title, content)
        }


    }
}