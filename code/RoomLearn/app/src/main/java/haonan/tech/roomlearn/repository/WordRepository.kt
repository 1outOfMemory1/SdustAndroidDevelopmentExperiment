package haonan.tech.roomlearn.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import haonan.tech.roomlearn.dao.WordDao
import haonan.tech.roomlearn.database.WordDatabase
import haonan.tech.roomlearn.entity.Word


// WordRepository 这个类是专门负责数据处理的   很重要
class WordRepository(var context: Context){
    private var allWordsLive:LiveData<List<Word>>
    private var wordDao:WordDao

    init {
        val wordDatabase: WordDatabase = WordDatabase.getWordDatabase(context)!!
        wordDao = wordDatabase.getWordDao()
        allWordsLive = wordDao.getAllWordsLive()
    }

    fun getAllWordsLive():LiveData<List<Word>>{
        return allWordsLive
    }

    fun insertWords(vararg param: Word){
        InsertAsyncTask(wordDao).execute(*param)
    }

    fun updateWords(vararg param: Word){
        UpdateAsyncTask(wordDao).execute(*param)
    }
    fun deleteWords(vararg param: Word){
        DeleteAsyncTask(wordDao).execute(*param)
    }
    fun deleteAllWords(){
        DeleteAllAsyncTask(wordDao).execute()
    }

    //  解释一下为什么需要通过四个内部类来实现  因为主线程不去做数据交互的事情
    // 因为耗时 会让用户感到卡顿  所以 利用异步来去做就比较合适
    // Room.databaseBuilder(context.applicationContext, WordDatabase::class.java,"word_database").build()
    //   WordDatabase 这个类实例化的时候  是不能在主线程运行的 只有加上 .allowMainThreadQueries() 才能运行


    class InsertAsyncTask(var wordDao: WordDao): AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg params: Word): Void? {
            wordDao.insertWord(*params)
            return  null
        }

    }
    class UpdateAsyncTask(var wordDao: WordDao): AsyncTask<Word, Void, Void>() {
        init {
            this.wordDao = wordDao
        }
        override fun doInBackground(vararg param: Word): Void? {
            wordDao.updateWord(*param)
            return null
        }
    }

    class DeleteAsyncTask(var wordDao: WordDao): AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg param: Word): Void? {
            wordDao.deleteWord(*param)
            return null
        }
    }

    class DeleteAllAsyncTask(var wordDao: WordDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            wordDao.deleteAllWords()
            return null
        }
    }
}