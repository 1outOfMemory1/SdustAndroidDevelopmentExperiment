package haonan.tech.experment4.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import haonan.tech.experment4.dao.ScDao
import haonan.tech.experment4.database.ScDatabase
import haonan.tech.experment4.entity.Sc


// ScRepository 这个类是专门负责数据处理的   很重要
class ScRepository(var context: Context){
    private var scDao:ScDao

    init {
        val wordDatabase: ScDatabase = ScDatabase.getScDatabase(context)!!
        scDao = wordDatabase.getScDao()
    }

    fun getAllScsLive(sno: Int):LiveData<List<Sc>>{
        return scDao.getAllScsLive(sno)
    }

    fun insertScs(vararg param: Sc){
        InsertAsyncTask(scDao).execute(*param)
    }

    fun updateScs(vararg param: Sc){
        UpdateAsyncTask(scDao).execute(*param)
    }

    fun deleteAllScs(){
        DeleteAllAsyncTask(scDao).execute()
    }

    fun deleteScBySnoAndCname(sc: Sc){
        DeleteBySnoAsyncTask(scDao).execute(sc)
    }

    //  解释一下为什么需要通过四个内部类来实现  因为主线程不去做数据交互的事情
    // 因为耗时 会让用户感到卡顿  所以 利用异步来去做就比较合适
    // Room.databaseBuilder(context.applicationContext, ScDatabase::class.java,"word_database").build()
    //   ScDatabase 这个类实例化的时候  是不能在主线程运行的 只有加上 .allowMainThreadQueries() 才能运行



    class DeleteBySnoAsyncTask(var scDao: ScDao): AsyncTask<Sc, Void, Void>() {
        override fun doInBackground(vararg params: Sc): Void? {
            scDao.deleteScBySnoAndCname(*params)
            return null
        }

    }

    class InsertAsyncTask(var scDao: ScDao): AsyncTask<Sc, Void, Void>() {
        override fun doInBackground(vararg params: Sc): Void? {
            scDao.insertSc(*params)
            return  null
        }

    }
    class UpdateAsyncTask(var scDao: ScDao): AsyncTask<Sc, Void, Void>() {
        init {
            this.scDao = scDao
        }
        override fun doInBackground(vararg param: Sc): Void? {
            scDao.updateSc(*param)
            return null
        }
    }



    class DeleteAllAsyncTask(var scDao: ScDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            scDao.deleteAllScs()
            return null
        }
    }
}