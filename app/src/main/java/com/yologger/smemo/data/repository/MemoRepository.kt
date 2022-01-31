package com.yologger.smemo.data.repository

import com.yologger.smemo.data.dao.MemoDao
import com.yologger.smemo.ui.dto.MemoDto

class MemoRepository (
    private val memoDao: MemoDao
) {
    suspend fun createMemo(memoDto: MemoDto): Long {
        return memoDao.insert(memoDto.toEntity())
    }
}