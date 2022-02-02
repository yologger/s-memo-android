package com.yologger.smemo.data.repository

import com.yologger.smemo.data.dao.MemoDao
import com.yologger.smemo.ui.dto.MemoDto

class MemoRepository (
    private val memoDao: MemoDao
) {
    suspend fun createMemo(memoDto: MemoDto): Long {
        return memoDao.insert(memoDto.toEntity())
    }

    suspend fun getAllMemos(): List<MemoDto> {
        return memoDao.getAll().map { MemoDto(title = it.title, content = it.content) }
    }

    suspend fun deleteAllMemos() {
        memoDao.deleteAll()
    }

    suspend fun getMemoById(id: Long): MemoDto {
        return memoDao.getById(id).toDto()
    }
}