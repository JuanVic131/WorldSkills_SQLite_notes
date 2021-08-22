package com.example.sqlite_app.repository

import com.example.sqlite_app.database.noteDao
import com.example.sqlite_app.model.Note

class Repository(var accesDao: noteDao) {
    // Note
    suspend fun addNote(note: Note) = accesDao.addNote(note)
    suspend fun updateNote(note: Note) = accesDao.updateNote(note)
    suspend fun deleteNote(note: Note) = accesDao.deleteNote(note)
    fun getNotes() = accesDao.getNotes()
}