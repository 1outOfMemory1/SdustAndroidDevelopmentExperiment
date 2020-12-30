package haonan.tech.experment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
class Course (
    @PrimaryKey(autoGenerate = true)
    var cno: Int = 0,
    @ColumnInfo(name = "cname")
    var cname : String = "",
    @ColumnInfo(name = "ccredit")
    var ccredit: String = ""
)
