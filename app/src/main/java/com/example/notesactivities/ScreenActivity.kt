package com.example.notesactivities

import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Common setup keeps all five screens clear of the system bars and keyboard.
abstract class ScreenActivity : AppCompatActivity() {
    protected fun showScreen(layout: Int) {
        enableEdgeToEdge()
        setContentView(layout)
        ViewCompat.setAccessibilityHeading(findViewById(R.id.screenTitle), true)
        val root = findViewById<View>(R.id.main)
        val padding = resources.getDimensionPixelSize(R.dimen.screen_padding)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val edges = insets.getInsets(WindowInsetsCompat.Type.systemBars() or
                WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime())
            view.setPadding(padding + edges.left, padding + edges.top,
                padding + edges.right, padding + edges.bottom)
            insets
        }
        ViewCompat.requestApplyInsets(root)
        findViewById<View>(R.id.backButton)?.setOnClickListener { finish() }
    }

    protected fun closeMissingNote() {
        Toast.makeText(this, R.string.note_missing, Toast.LENGTH_LONG).show()
        finish()
    }

    companion object {
        const val EXTRA_NOTE_ID = "com.example.notesactivities.NOTE_ID"
    }
}
