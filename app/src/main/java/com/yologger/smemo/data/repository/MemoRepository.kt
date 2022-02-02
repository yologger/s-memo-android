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
        return memoDao.getAll().map { MemoDto(id = it.id, title = it.title, content = it.content) }
    }

    suspend fun deleteAllMemos() {
        memoDao.deleteAll()
    }

    suspend fun getMemoById(id: Long): MemoDto {
        return memoDao.getById(id).toDto()
    }

    suspend fun updateMemo(memoDto: MemoDto): MemoDto {
        memoDao.update(memoDto.toEntity())
        return memoDao.getById(memoDto.id).toDto()
    }
}