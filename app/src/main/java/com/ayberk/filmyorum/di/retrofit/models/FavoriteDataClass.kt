package com.ayberk.filmyorum.di.retrofit.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class FavoriteDataClass (

    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "vote_average")
    val vote_average:Double,
    @ColumnInfo(name = "poster_path")
    val poster_path:String,
    @ColumnInfo(name = "release_date")
    val release_date:String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,



    )


