package com.gcalvocr.notes.domain.repositories

import com.gcalvocr.notes.domain.models.NoteModel

interface NoteRepository {
    fun getAllNotes(): List<NoteModel>
    fun addNote(note: NoteModel): NoteModel?
    fun deleteNote(id: Long): Boolean
    fun updateNote(note: NoteModel)
}