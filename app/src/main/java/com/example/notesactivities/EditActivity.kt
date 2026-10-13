package com.example.notesactivities

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class EditActivity : ScreenActivity() {
    private var noteId: Int? = null
    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var contentInput: EditText
    private lateinit var doneInput: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen(R.layout.activity_edit)
        noteId = if (intent.hasExtra(EXTRA_NOTE_ID)) intent.getIntExtra(EXTRA_NOTE_ID, -1) else null
        val existing = noteId?.let { NotesRepository.findById(it) }
        if (noteId != null && existing == null) return closeMissingNote()

        titleInput = findViewById(R.id.titleInput)
        descriptionInput = findViewById(R.id.descriptionInput)
        contentInput = findViewById(R.id.contentInput)
        doneInput = findViewById(R.id.doneInput)
        findViewById<TextView>(R.id.screenTitle).setText(
            if (noteId == null) R.string.new_note else R.string.edit_note)
        // Android restores inputs after rotation; prefill only the first time.
        if (savedInstanceState == null && existing != null) {
            titleInput.setText(existing.title)
            descriptionInput.setText(existing.shortDescription)
            contentInput.setText(existing.content)
            doneInput.isChecked = existing.status == NoteStatus.DONE
        }
        findViewById<View>(R.id.saveButton).setOnClickListener { saveNote() }
        findViewById<View>(R.id.cancelButton).setOnClickListener { finish() }
    }

    private fun saveNote() {
        val title = titleInput.text.toString().trim()
        if (title.isEmpty()) {
            titleInput.error = getString(R.string.title_required)
            titleInput.requestFocus()
            return
        }
        val description = descriptionInput.text.toString().trim()
        val content = contentInput.text.toString().trim()
        val status = if (doneInput.isChecked) NoteStatus.DONE else NoteStatus.TODO
        val id = noteId
        if (id == null) {
            NotesRepository.add(title, description, content, status)
        } else if (!NotesRepository.update(Note(id, title, description, content, status))) {
            return closeMissingNote()
        }
        finish()
    }
}
