package com.gcalvocr.notes.domain.usecases

import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository

// Ellos no deben de guardar datos, solo ejecutar
class GetNotesUseCase(
    private val noteRepository: NoteRepository)
{
    fun execute():List<NoteModel> = noteRepository.getAllNotes()
}