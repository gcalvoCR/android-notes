package com.gcalvocr.notes.domain.repositories

import com.gcalvocr.notes.domain.models.NoteModel

interface NoteRepository {
    fun getAllNotes(): List<NoteModel>
}