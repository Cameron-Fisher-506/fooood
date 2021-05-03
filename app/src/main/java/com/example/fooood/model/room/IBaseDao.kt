package com.example.fooood.model.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface IBaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun <T> insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun <T> insert(entities: List<T>): List<Long>

    @Update
    suspend fun <T> update(entity: T)

    @Update
    suspend fun <T> update(entities: List<T>)

    @Delete
    suspend fun <T> delete(entity: T)

    @Delete
    suspend fun <T> delete(entities: List<T>)

    @RawQuery
    suspend fun <T> specialQuery(query: SupportSQLiteQuery): List<T>?
}