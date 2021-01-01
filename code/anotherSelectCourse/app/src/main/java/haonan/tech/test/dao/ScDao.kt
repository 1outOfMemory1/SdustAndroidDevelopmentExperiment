package haonan.tech.experment4.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import haonan.tech.experment4.entity.Sc

@Dao
interface ScDao {
    @Insert
    fun insertSc(vararg arg: Sc)

    @Update
    fun updateSc(vararg arg: Sc)

    @Delete
    fun deleteScBySnoAndCno(vararg sc:Sc)

    @Query("DELETE FROM sc")
    fun deleteAllScs()

    @Query("SELECT * FROM sc where sno = :sno")
    fun getAllScsLive(vararg sno: Int): LiveData<List<Sc>>

    @Update()
    fun updateSc(sc: Sc)

}