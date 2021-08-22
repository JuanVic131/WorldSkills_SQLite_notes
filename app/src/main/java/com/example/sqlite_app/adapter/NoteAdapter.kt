package com.example.sqlite_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_app.R
import com.example.sqlite_app.model.Note
import kotlinx.android.synthetic.main.card_note.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private var pokemonList: List<Note> = listOf()

    fun setNotes(pokemonList: List<Note>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    private fun ViewGroup.inflate(
        @LayoutRes layoutRes: Int,
        attachRoot: Boolean = false
    ): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.card_note, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txt_title.text = pokemonList[position].title
        holder.itemView.txt_description.text = pokemonList[position].description
    }

    override fun getItemCount() = pokemonList.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val item: Note = pokemonList[adapterPosition]
        }

    }
}