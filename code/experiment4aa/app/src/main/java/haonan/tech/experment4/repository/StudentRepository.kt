package haonan.tech.experment4.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import haonan.tech.experment4.dao.StudentDao
import haonan.tech.experment4.database.StudentDatabase
import haonan.tech.experment4.entity.Student


// StudentRepository 这个类是专门负责数据处理的   很重要
class StudentRepository(var context: Context){
    private var allStudentsLive:LiveData<List<Student>>
    private var studentDao:StudentDao

    init {
        val wordDatabase: StudentDatabase = StudentDatabase.getStudentDatabase(context)!!
        studentDao = wordDatabase.getStudentDao()
        allStudentsLive = studentDao.getAllStudentsLive()
    }

    fun getAllStudentsLive():LiveData<List<Student>>{
        return allStudentsLive
    }

    fun insertStudents(vararg param: Student){
        InsertAsyncTask(studentDao).execute(*param)
    }

    fun updateStudents(vararg param: Student){
        UpdateAsyncTask(studentDao).execute(*param)
    }

    fun deleteAllStudents(){
        DeleteAllAsyncTask(studentDao).execute()
    }

    //  解释一下为什么需要通过四个内部类来实现  因为主线程不去做数据交互的事情
    // 因为耗时 会让用户感到卡顿  所以 利用异步来去做就比较合适
    // Room.databaseBuilder(context.applicationContext, StudentDatabase::class.java,"word_database").build()
    //   StudentDatabase 这个类实例化的时候  是不能在主线程运行的 只有加上 .allowMainThreadQueries() 才能运行


    class InsertAsyncTask(var studentDao: StudentDao): AsyncTask<Student, Void, Void>() {
        override fun doInBackground(vararg params: Student): Void? {
            studentDao.insertStudent(*params)
            return  null
        }

    }
    class UpdateAsyncTask(var studentDao: StudentDao): AsyncTask<Student, Void, Void>() {
        init {
            this.studentDao = studentDao
        }
        override fun doInBackground(vararg param: Student): Void? {
            studentDao.updateStudent(*param)
            return null
        }
    }



    class DeleteAllAsyncTask(var studentDao: StudentDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            studentDao.deleteAllStudents()
            return null
        }
    }
}