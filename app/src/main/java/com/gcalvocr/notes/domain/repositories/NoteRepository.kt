package com.gcalvocr.notes.domain.repositories

import com.gcalvocr.notes.domain.models.NoteModel

interface NoteRepository {
    fun getAllNotes(): List<NoteModel>
    fun addNote(title: String, description: String, tag: String): NoteModel?
    fun deleteNote(id: Int): Boolean
    fun updateNote(note: NoteModel)
}