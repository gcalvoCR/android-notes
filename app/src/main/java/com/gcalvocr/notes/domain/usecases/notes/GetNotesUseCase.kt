package com.gcalvocr.notes.domain.usecases.notes

import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository

// Los usecases no deben de guardar datos, solo ejecutar
class GetNotesUseCase(
    private val noteRepository: NoteRepository)
{
    fun execute():List<NoteModel> = noteRepository.getAllNotes()
}