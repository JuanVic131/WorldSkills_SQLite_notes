package com.example.sqlite_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite_app.adapter.NoteAdapter
import com.example.sqlite_app.model.Note
import com.example.sqlite_app.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var adapter = NoteAdapter()
    lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instanciamos nuestro NoteViewModel
        viewModel = NoteViewModel(this.application)

        // Se cargan al principio
        cargarNotes()

        btn_addnote.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val note = Note(0, "nota 1", "Descripcion 1")

        when (v?.id) {
            btn_addnote.id -> viewModel.addNote(note)
            btn_update.id -> viewModel.updateNote(ultimaNota())
            btn_delete.id -> viewModel.deleteNote(ultimaNota())
        }

        cargarNotes()
    }

    private fun ultimaNota(): Note {
        var lastNote : Note? = null

        viewModel.getNotes.observe(this) {

            lastNote = it[it.size - 1]
        }

        lastNote?.title = "Nota no. ${lastNote?.id}"
        lastNote?.description = "Descripción no. ${lastNote?.id}"

        return lastNote!!
    }

    private fun cargarNotes() = viewModel.getNotes.observe(this) {

        if (it != null) {
            cargarRecyclerViewNotas(it)
        }
    }

    private fun cargarRecyclerViewNotas (it: List<Note>) {
        NoteAdapter().setNotes(it)
        recyclerInit(it)
    }

    private fun recyclerInit(top: List<Note>) {
        adapter = NoteAdapter()
        adapter.setNotes(top)
        recyclerView.layoutManager =
            LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }
}