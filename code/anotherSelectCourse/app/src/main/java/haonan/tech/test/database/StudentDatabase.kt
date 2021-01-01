package haonan.tech.experment4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import haonan.tech.experment4.dao.StudentDao
import haonan.tech.experment4.dao.WordDao
import haonan.tech.experment4.entity.Student

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase: RoomDatabase(){
    abstract fun getStudentDao(): StudentDao
    companion object {
        var  INSTANCE: StudentDatabase? = null
        fun getStudentDatabase(context: Context):StudentDatabase?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, StudentDatabase::class.java,"student_database")
                    .build()
            }
            return INSTANCE
        }
    }
}
