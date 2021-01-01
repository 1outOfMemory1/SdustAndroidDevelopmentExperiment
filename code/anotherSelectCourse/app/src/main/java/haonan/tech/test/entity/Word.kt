package haonan.tech.experment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word (
        @PrimaryKey(autoGenerate = true) var id:Int? = 0,
        @ColumnInfo(name = "english_word") var word:String? = "",
        @ColumnInfo(name = "chinese_meaning") var chineseMeaning:String? = ""
)
