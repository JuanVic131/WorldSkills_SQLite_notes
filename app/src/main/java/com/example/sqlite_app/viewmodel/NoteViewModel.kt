package com.example.sqlite_app.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlite_app.database.NoteDatabase
import com.example.sqlite_app.model.Note
import com.example.sqlite_app.repository.Repository
import kotlinx.coroutines.launch

class NoteViewModel(app : Application) : ViewModel() {
    // Se hace instancia de la Base de Datos
    private val noteDatabase = NoteDatabase.getDatabase(app).noteDao()

    private val repository : Repository

    val getNotes : LiveData<List<Note>>

    init {
        repository = Repository(noteDatabase)
        getNotes = repository.getNotes()
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}