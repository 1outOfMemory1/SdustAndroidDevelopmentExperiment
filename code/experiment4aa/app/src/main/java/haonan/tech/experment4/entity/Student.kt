package haonan.tech.experment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class Student(
    @PrimaryKey(autoGenerate = true) var sno: Int? = 0,
    @ColumnInfo(name = "sname") var sname:String? = "",
    @ColumnInfo(name = "ssex") var ssex:String? = "",
    @ColumnInfo(name = "sage") var sage:Int? = 18
)