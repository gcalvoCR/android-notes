package com.gcalvocr.notes.domain.usecases.notes

import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository

// Ellos no deben de guardar datos, solo ejecutar
class AddNotesUseCase(
    private val noteRepository: NoteRepository)
{
    fun execute(note: NoteModel): NoteModel? = noteRepository.addNote(note)
}