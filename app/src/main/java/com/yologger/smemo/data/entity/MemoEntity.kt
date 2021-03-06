package com.yologger.smemo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yologger.smemo.ui.dto.MemoDto

@Entity
data class MemoEntity
constructor(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String
) {
    fun toDto(): MemoDto {
        return MemoDto(id = this.id, title = this.title, content = this.content)
    }
}