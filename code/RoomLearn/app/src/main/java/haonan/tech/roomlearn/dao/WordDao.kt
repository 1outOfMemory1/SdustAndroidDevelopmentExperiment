package haonan.tech.roomlearn.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import haonan.tech.roomlearn.entity.Word

@Dao // 英文缩写 database access object   增删改查只需要写个函数接口就行了 其他不用管
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