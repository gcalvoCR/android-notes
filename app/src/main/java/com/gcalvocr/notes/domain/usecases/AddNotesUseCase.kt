package com.gcalvocr.notes.domain.usecases

import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository

// Ellos no deben de guardar datos, solo ejecutar
class AddNotesUseCase(
    private val noteRepository: NoteRepository)
{
    fun execute(title: String, description: String, tag: String): NoteModel? = noteRepository.addNote(title, description, tag)
}