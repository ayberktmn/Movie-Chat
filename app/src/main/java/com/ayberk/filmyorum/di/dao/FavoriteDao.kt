package com.ayberk.filmyorum.di.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ayberk.filmyorum.di.retrofit.models.FavoriteDataClass

@Dao
interface FavoriteDao {
    @Delete
    fun delete(advent: FavoriteDataClass)
    @Insert
    fun insert(advent: FavoriteDataClass)
    @Query("SELECT * FROM favoritedataclass")
    fun getAll(): List<FavoriteDataClass>

    @Query("SELECT COUNT(*) FROM favoritedataclass WHERE id = :id")
    fun checkIfDataExists(id: Int): Int
}