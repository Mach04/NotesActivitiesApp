package com.example.notesactivities

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView

// The fifth Activity summarizes the same shared list used by the other screens.
class StatisticsActivity : ScreenActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen(R.layout.activity_statistics)
    }

    override fun onResume() {
        super.onResume()
        val notes = NotesRepository.notes
        val done = notes.count { it.status == NoteStatus.DONE }
        val percent = if (notes.isEmpty()) 0 else done * 100 / notes.size
        findViewById<TextView>(R.id.totalCount).text = getString(R.string.total_count, notes.size)
        findViewById<TextView>(R.id.todoCount).text = getString(R.string.todo_count, notes.size - done)
        findViewById<TextView>(R.id.doneCount).text = getString(R.string.done_count, done)
        findViewById<TextView>(R.id.completionText).text = getString(R.string.completion_percent, percent)
        findViewById<ProgressBar>(R.id.completionProgress).apply {
            progress = percent
            contentDescription = getString(R.string.completion_percent, percent)
        }
    }
}
