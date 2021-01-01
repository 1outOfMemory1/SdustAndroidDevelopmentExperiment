package haonan.tech.experment4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import haonan.tech.experment4.dao.ScDao
import haonan.tech.experment4.entity.Sc

@Database(entities = [Sc::class], version = 1)
abstract class ScDatabase: RoomDatabase(){
    abstract fun getScDao(): ScDao
    companion object {
        var  INSTANCE: ScDatabase? = null
        fun getScDatabase(context: Context):ScDatabase?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, ScDatabase::class.java,"sc_database")
                    .build()
            }
            return INSTANCE
        }
    }
}
