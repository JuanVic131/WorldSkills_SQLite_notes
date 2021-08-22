package com.example.sqlite_app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sqlite_app.model.Note

@Dao
interface noteDao {
    // CRUD a la base de datos

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes")
    fun getNotes() : LiveData<List<Note>>
}