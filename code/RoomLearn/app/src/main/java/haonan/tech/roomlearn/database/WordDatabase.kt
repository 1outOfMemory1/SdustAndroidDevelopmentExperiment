package haonan.tech.roomlearn.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import haonan.tech.roomlearn.dao.WordDao
import haonan.tech.roomlearn.entity.Word

@Database(entities = [Word::class], version = 1)
abstract class   WordDatabase :RoomDatabase(){
    abstract fun getWordDao():WordDao
    companion object {
        var  INSTANCE: WordDatabase? = null
        fun getWordDatabase(context: Context):WordDatabase?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java,"word_database")
                        .build()
            }
            return INSTANCE
        }
    }
}