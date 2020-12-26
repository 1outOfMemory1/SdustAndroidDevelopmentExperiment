package haonan.tech.roomlearn.dao

import androidx.room.*
import haonan.tech.roomlearn.entity.Word

@Dao // database access object
interface WordDao {
    @Insert
    fun insertWord(word:Word, vararg wordx:Word)

    @Update
    fun updateWord(word:Word, vararg wordx:Word)

    @Delete
    fun deleteWord(word:Word, vararg wordx:Word)

    @Query("DELETE FROM word")
    fun deleteAllWords()
    @Query("SELECT * FROM WORD ORDER BY ID DESC ")
    fun getAllWords():List<Word>

}