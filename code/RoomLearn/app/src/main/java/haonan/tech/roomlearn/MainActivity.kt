package haonan.tech.roomlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import haonan.tech.roomlearn.entity.Word
import haonan.tech.roomlearn.viewModel.WordVIewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // wordViewModel 这个参数是专门负责视图和
    private lateinit var wordViewModel: WordVIewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(WordVIewModel::class.java)
        wordViewModel.getAllWordsLive().observe(this, Observer {
            var textStr: String = ""
            it.forEach { ele -> textStr += ele.id.toString() + ":" + ele.word + "=" + ele.chineseMeaning + "\n" }
            myTextView.text = textStr
        })
        //  insert 按钮的点击事件监听  每点击一次 插入数据
        insertBtn.setOnClickListener {
            val tempWord1: Word = Word(null, "hello", "你好")
            val tempWord2: Word = Word(null, "world", "世界")
            wordViewModel.insertWords(tempWord1, tempWord2)
        }
        //   clear 按钮的点击事件监听  每点击一次 清空数据库中的所有数据
        clearBtn.setOnClickListener {
            wordViewModel.deleteAllWords()
        }
    }
}