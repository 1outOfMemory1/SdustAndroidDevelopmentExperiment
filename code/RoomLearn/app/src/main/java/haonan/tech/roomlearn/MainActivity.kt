package haonan.tech.roomlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import haonan.tech.roomlearn.dao.WordDao
import haonan.tech.roomlearn.database.WordDatabase
import haonan.tech.roomlearn.entity.Word
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var wordDatabase:WordDatabase
    lateinit var wordDao:WordDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordDatabase = Room.databaseBuilder(applicationContext, WordDatabase::class.java,"word_database")
                .allowMainThreadQueries()
                .build()
        wordDao = wordDatabase.getWordDao()
        insertBtn.setOnClickListener {
            val  tempWord1:Word = Word(null,"hello","你好")
            val  tempWord2:Word = Word(null,"world","世界")
            wordDao.insertWord(tempWord1,tempWord2)
            updateView()
        }
        clearBtn.setOnClickListener {
            wordDao.deleteAllWords()
            updateView()
        }

    }
    fun updateView(){
        var textStr:String = ""
        val wordsList:List<Word> = wordDao.getAllWords()
        wordsList.forEach { ele ->  textStr += ele.id.toString() + ":" + ele.word +  "=" + ele.chineseMeaning  + "\n"}
        myTextView.text = textStr

    }
}