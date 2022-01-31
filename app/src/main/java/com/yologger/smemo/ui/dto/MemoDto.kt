package com.yologger.smemo.ui.dto

import com.yologger.smemo.data.entity.MemoEntity

data class MemoDto(
    private val title: String,
    private val content: String
) {
    fun toEntity(): MemoEntity {
        return MemoEntity(title = this.title, content = this.content)
    }
}