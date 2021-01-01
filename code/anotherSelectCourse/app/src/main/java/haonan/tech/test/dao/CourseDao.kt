package haonan.tech.experment4.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import haonan.tech.experment4.entity.Course

interface CourseDao {
    @Insert
    fun insertCourse(vararg arg: Course)

    @Update
    fun updateCourse(vararg arg: Course)

    @Query("DELETE FROM course WHERE cno = :cno ")
    fun deleteCourseByCno(cno: Int)

    @Query("DELETE FROM course")
    fun deleteAllCourses()

    @Query("SELECT * FROM course ORDER BY cno DESC ")
    fun getAllCoursesLive(): LiveData<List<Course>>

}