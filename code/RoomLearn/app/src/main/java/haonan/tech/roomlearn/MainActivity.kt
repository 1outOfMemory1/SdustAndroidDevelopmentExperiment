package haonan.tech.roomlearn

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import haonan.tech.roomlearn.dao.WordDao
import haonan.tech.roomlearn.database.WordDatabase
import haonan.tech.roomlearn.entity.Word
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var wordDatabase:WordDatabase
    lateinit var wordDao:WordDao
    lateinit var allWordsLive:LiveData<List<Word>>


    companion object{


    }

    class InsertAsyncTask(var wordDao:WordDao): AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            wordDao.insertWord(*params)
            return  null
        }

    }
    class UpdateAsyncTask(var wordDao:WordDao): AsyncTask<Word, Void, Void>() {
        init {
            this.wordDao = wordDao
        }
        override fun doInBackground(vararg param: Word): Void? {
            wordDao.updateWord(*param)
            return null
        }
    }

    class DeleteAsyncTask(var wordDao:WordDao): AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg param: Word): Void? {
            wordDao.deleteWord(*param)
            return null
        }
    }

    class DeleteAllAsyncTask(var wordDao:WordDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            wordDao.deleteAllWords()
            return null
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordDatabase = Room.databaseBuilder(applicationContext, WordDatabase::class.java,"word_database")
                .build()
        wordDao = wordDatabase.getWordDao()
        allWordsLive = wordDao.getAllWordsLive()

        allWordsLive.observe(this, Observer {
            var textStr:String = ""
            it.forEach { ele ->  textStr += ele.id.toString() + ":" + ele.word +  "=" + ele.chineseMeaning  + "\n"}
            myTextView.text = textStr
        })
        insertBtn.setOnClickListener {
            val  tempWord1:Word = Word(null,"hello","你好")
            val  tempWord2:Word = Word(null,"world","世界")
            InsertAsyncTask(wordDao).execute(tempWord1,tempWord2)
        }
        clearBtn.setOnClickListener {
            DeleteAllAsyncTask(wordDao).execute()
        }

    }




}