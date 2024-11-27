package com.darleyleal.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "year")
    var year: String? = null,

    @ColumnInfo(name = "category")
    var category: String? = null,

    @ColumnInfo(name = "youtubeLinkVideo")
    var youtubeLinkVideo: String? = null
)