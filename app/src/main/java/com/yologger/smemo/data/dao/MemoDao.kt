package com.yologger.smemo.data.dao

import androidx.room.*
import com.yologger.smemo.data.entity.MemoEntity

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(memoEntity: MemoEntity): Long

    @Query("SELECT * FROM MemoEntity")
    suspend fun getAll(): List<MemoEntity>

    @Query("SELECT * FROM MemoEntity WHERE id = :id")
    suspend fun getById(id: Long): MemoEntity

    @Query("DELETE FROM MemoEntity")
    suspend fun deleteAll()
}