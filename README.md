# NotesActivitiesApp

An Android notes app by **Manu**, created for Lab 2 of Programming of Mobile
Applications for Android. This project implements variant 2A-1: Kotlin and
XML layouts with multiple Activities.

## Features

- **Home:** RecyclerView with custom rows showing title, one-line summary, and TODO/DONE status.
- **Details:** open a selected note using its ID, read its full content, and navigate to editing.
- **Add/Edit:** a shared form with title validation, summary, content, status, Save, and Cancel.
- **About:** author and course information.
- **Statistics:** total notes, TODO/DONE counts, and completion percentage.

Three sample notes are included. Notes are intentionally stored **in memory**,
as required by the lab: changes reset when the app process ends. There is no
database or online account requirement.

## Project structure

The Kotlin sources are in `app/src/main/java/com/example/notesactivities`.

- `Note.kt` defines the note model and status values.
- `NotesRepository.kt` owns the shared list and assigns note IDs.
- `NotesAdapter.kt` connects notes to RecyclerView rows using ListAdapter and DiffUtil.
- The five Activity classes implement the screens and Intent navigation.
- `ScreenActivity.kt` shares system-bar and keyboard inset handling.
- `app/src/main/res/layout` contains the XML screen and row layouts.

## Development environment

Use VS Code or Android Studio with JDK 21, Android SDK platform 36, and Android
build tools 36.0.0. The Gradle 8.13 wrapper is included. The app targets Android
API 36 and supports API 24 or later.

Configure `JAVA_HOME` and `ANDROID_HOME` for your own installations, or specify
your SDK location in a local `local.properties` file. Machine-specific editor
settings, SDK paths, build outputs, caches, and signing keys are excluded from Git.
The original lab's portable toolchain is separate from this repository.

## Verification

The debug build and Android lint completed on 13 October 2026 with no errors.
Four non-blocking lint warnings remain: three dependency-version notices and
one backup-configuration notice.

Emulator checks on Android 16 covered all five screens, correct note selection,
empty/whitespace title validation, creation, prefilled editing, both Cancel
paths, saved text/status changes, updated statistics without duplicate notes,
and reset after process restart.
