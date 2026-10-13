package com.example.notesactivities

// An enum limits the status to these two valid values.
enum class NoteStatus {
    TODO,
    DONE
}

// A data class describes the information stored in one note.
data class Note(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val content: String,
    val status: NoteStatus = NoteStatus.TODO
)
