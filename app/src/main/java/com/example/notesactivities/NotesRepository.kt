package com.example.notesactivities

// Kotlin's object creates one shared instance for this app process.
// This list lives in memory; its changes are lost when the process ends.
object NotesRepository {
    private val storedNotes = mutableListOf(
        Note(
            id = 1,
            title = "Prepare for class",
            shortDescription = "Review the Android lab instructions.",
            content = "Read the lab instructions and identify the required screens. " +
                "Bring questions about Activities and navigation to class.",
            status = NoteStatus.TODO
        ),
        Note(
            id = 2,
            title = "Read Kotlin notes",
            shortDescription = "Review data classes and objects.",
            content = "Review how a data class stores related values and how an object " +
                "provides a shared instance. Try identifying both in the Notes project.",
            status = NoteStatus.DONE
        ),
        Note(
            id = 3,
            title = "Plan the weekend",
            shortDescription = "Make time for a walk and some reading.",
            content = "Choose a walking route and a book to read. " +
                "Leave some free time to relax.",
            status = NoteStatus.TODO
        )
    )

    // Screens receive a snapshot; only add/update can change the shared list.
    val notes: List<Note> get() = storedNotes.toList()
    private var nextId = 4

    // Look up the ID rather than assuming it is the list position.
    fun findById(id: Int): Note? = storedNotes.find { it.id == id }

    fun add(title: String, description: String, content: String, status: NoteStatus) {
        require(title.isNotBlank())
        storedNotes.add(Note(nextId++, title.trim(), description, content, status))
    }

    fun update(note: Note): Boolean {
        require(note.title.isNotBlank())
        val index = storedNotes.indexOfFirst { it.id == note.id }
        if (index == -1) return false
        storedNotes[index] = note.copy(title = note.title.trim())
        return true
    }
}
