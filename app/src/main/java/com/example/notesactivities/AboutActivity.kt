package com.example.notesactivities

import android.os.Bundle

class AboutActivity : ScreenActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen(R.layout.activity_about)
    }
}
