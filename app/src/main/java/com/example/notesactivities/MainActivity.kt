package com.example.notesactivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ScreenActivity() {
    private val notesAdapter = NotesAdapter { noteId ->
        startActivity(Intent(this, DetailActivity::class.java).putExtra(EXTRA_NOTE_ID, noteId))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen(R.layout.activity_main)
        findViewById<RecyclerView>(R.id.notesList).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = notesAdapter
        }
        findViewById<View>(R.id.addNoteButton).setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
        findViewById<View>(R.id.aboutButton).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        findViewById<View>(R.id.statisticsButton).setOnClickListener {
            startActivity(Intent(this, StatisticsActivity::class.java))
        }
    }

    // Refresh when returning from another Activity, including after a save.
    override fun onResume() {
        super.onResume()
        val notes = NotesRepository.notes
        notesAdapter.submitList(notes)
        findViewById<View>(R.id.notesList).visibility = if (notes.isEmpty()) View.GONE else View.VISIBLE
        findViewById<View>(R.id.emptyState).visibility = if (notes.isEmpty()) View.VISIBLE else View.GONE
    }
}
