package haonan.tech.experment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE


/*
   foreignKeys = arrayOf(
        ForeignKey(entity = Student::class,
            parentColumns = arrayOf("sno"),
            childColumns = arrayOf("sno"),
            onDelete = CASCADE),
        ForeignKey(entity = Course::class,
            parentColumns = arrayOf("cname"),
            childColumns = arrayOf("cname"),
            onDelete = CASCADE)
    )
 */

@Entity(tableName = "sc",
    primaryKeys = ["sno","cno"]
 )
class Sc (
    @ColumnInfo(name = "sno")
    var sno: Int = 0,
    @ColumnInfo(name = "cno")
    var cno : Int,
    @ColumnInfo(name = "cname")
    var cname : String,
    @ColumnInfo(name = "grade")
    var grade : Int = 100
)