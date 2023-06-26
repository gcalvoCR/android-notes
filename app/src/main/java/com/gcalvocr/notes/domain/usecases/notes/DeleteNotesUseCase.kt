package com.gcalvocr.notes.domain.usecases.notes

import com.gcalvocr.notes.domain.repositories.NoteRepository

// Ellos no deben de guardar datos, solo ejecutar
class DeleteNotesUseCase(
    private val noteRepository: NoteRepository)
{
    fun execute(id: Long): Boolean = noteRepository.deleteNote(id)
}