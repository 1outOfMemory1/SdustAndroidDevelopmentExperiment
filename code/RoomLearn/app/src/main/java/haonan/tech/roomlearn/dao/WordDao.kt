package haonan.tech.roomlearn.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import haonan.tech.roomlearn.entity.Word

@Dao // database access object
interface WordDao {
    @Insert
    fun insertWord(vararg arg:Word)

    @Update
    fun updateWord(vararg arg:Word)

    @Delete
    fun deleteWord(vararg arg:Word)

    @Query("DELETE FROM word")
    fun deleteAllWords()
    @Query("SELECT * FROM WORD ORDER BY ID DESC ")
    fun getAllWordsLive():LiveData<List<Word>>

}