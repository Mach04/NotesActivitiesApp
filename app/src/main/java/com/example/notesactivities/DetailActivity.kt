package com.example.notesactivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DetailActivity : ScreenActivity() {
    private val noteId: Int get() = intent.getIntExtra(EXTRA_NOTE_ID, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen(R.layout.activity_detail)
        findViewById<View>(R.id.editButton).setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java).putExtra(EXTRA_NOTE_ID, noteId))
        }
    }

    override fun onResume() {
        super.onResume()
        val note = NotesRepository.findById(noteId) ?: return closeMissingNote()
        findViewById<TextView>(R.id.detailTitle).text = note.title
        findViewById<TextView>(R.id.detailDescription).text = note.shortDescription
        findViewById<TextView>(R.id.detailContent).text = note.content
        findViewById<TextView>(R.id.detailStatus).text = getString(R.string.note_status, note.status.name)
    }
}
