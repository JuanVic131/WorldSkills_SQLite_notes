package com.example.sqlite_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var description: String
    )