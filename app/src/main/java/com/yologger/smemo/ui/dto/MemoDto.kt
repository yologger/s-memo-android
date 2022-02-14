package com.yologger.smemo.ui.dto

import com.yologger.smemo.data.entity.MemoEntity
import java.io.Serializable

data class MemoDto(
    val id: Long = 0,
    val title: String,
    val content: String
) : Serializable {
    private val serialVersionUID = 1L

    fun toEntity(): MemoEntity {
        return MemoEntity(id = this.id, title = this.title, content = this.content)
    }
}