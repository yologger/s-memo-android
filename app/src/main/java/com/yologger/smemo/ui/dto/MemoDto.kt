package com.yologger.smemo.ui.dto

import com.yologger.smemo.data.entity.MemoEntity

data class MemoDto(
    val id: Long = 0,
    val title: String,
    val content: String
) {
    fun toEntity(): MemoEntity {
        return MemoEntity(title = this.title, content = this.content)
    }
}