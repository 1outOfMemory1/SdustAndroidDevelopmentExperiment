package haonan.tech.roomlearn.database

import androidx.room.Database
import androidx.room.RoomDatabase
import haonan.tech.roomlearn.dao.WordDao
import haonan.tech.roomlearn.entity.Word

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase :RoomDatabase(){
    abstract fun getWordDao():WordDao
}