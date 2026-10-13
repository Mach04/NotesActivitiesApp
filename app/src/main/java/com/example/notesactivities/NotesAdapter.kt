package com.example.notesactivities

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil

// The adapter connects the list of Note objects to the RecyclerView's rows.
class NotesAdapter(private val onNoteClick: (Int) -> Unit) :
    ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiff) {

    init {
        setHasStableIds(true)
    }

    // Create a row when RecyclerView needs another ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    // Fill a row with the note at this position; old rows can be reused.
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
        holder.itemView.setOnClickListener { onNoteClick(note.id) }
    }

    override fun getItemId(position: Int): Long = getItem(position).id.toLong()

    // IDs identify notes; equality detects changed fields after editing.
    private object NoteDiff : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }

    // Store references to each row's views so binding doesn't repeatedly find them.
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.noteTitle)
        private val description: TextView = itemView.findViewById(R.id.noteDescription)
        private val status: TextView = itemView.findViewById(R.id.noteStatus)

        fun bind(note: Note) {
            title.text = note.title
            description.text = note.shortDescription

            val isDone = note.status == NoteStatus.DONE
            status.setText(if (isDone) R.string.status_done else R.string.status_todo)
            status.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (isDone) R.color.done_text else R.color.todo_text
                )
            )
            status.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    itemView.context,
                    if (isDone) R.color.done_background else R.color.todo_background
                )
            )
        }
    }
}
