package haonan.tech.experment4.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import haonan.tech.experment4.entity.Student
import haonan.tech.experment4.entity.Word

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(vararg arg: Student)

    @Update
    fun updateStudent(vararg arg: Student)

    @Query("DELETE FROM student WHERE sno = :sno ")
    fun deleteStudentBySno(sno: Int)

    @Query("DELETE FROM student")
    fun deleteAllStudents()

    @Query("SELECT * FROM student ORDER BY sno DESC ")
    fun getAllStudentsLive(): LiveData<List<Student>>


    @Update()
    fun updateStudent(student:Student)

}