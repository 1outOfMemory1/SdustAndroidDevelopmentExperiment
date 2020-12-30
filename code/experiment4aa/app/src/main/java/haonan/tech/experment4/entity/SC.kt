package haonan.tech.experment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE


@Entity(tableName = "sc",
    primaryKeys = ["sno","cno"],
    foreignKeys = arrayOf(
        ForeignKey(entity = Student::class,
            parentColumns = arrayOf("sno"),
            childColumns = arrayOf("sno"),
            onDelete = CASCADE),
        ForeignKey(entity = Course::class,
            parentColumns = arrayOf("cno"),
            childColumns = arrayOf("cno"),
            onDelete = CASCADE)
    ))
class SC (
    @ColumnInfo(name = "sno")
    var sno: Int = 0,
    @ColumnInfo(name = "cno")
    var cno : Int = 0,
    @ColumnInfo(name = "cname")
    var cname : String,
    @ColumnInfo(name = "grade")
    var grade : Int = 100
)