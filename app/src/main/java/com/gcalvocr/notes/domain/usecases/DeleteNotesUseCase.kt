package com.gcalvocr.notes.domain.usecases

import com.gcalvocr.notes.domain.repositories.NoteRepository

// Ellos no deben de guardar datos, solo ejecutar
class DeleteNotesUseCase(
    private val noteRepository: NoteRepository)
{
    fun execute(id: Int): Boolean = noteRepository.deleteNote(id)
}