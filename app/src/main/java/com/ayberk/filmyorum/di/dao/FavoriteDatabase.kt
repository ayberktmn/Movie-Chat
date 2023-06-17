package com.ayberk.filmyorum.di.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayberk.filmyorum.di.retrofit.models.FavoriteDataClass

@Database(entities = [FavoriteDataClass::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}