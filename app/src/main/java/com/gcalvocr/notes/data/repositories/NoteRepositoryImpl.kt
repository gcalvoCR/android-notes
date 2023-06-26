package com.gcalvocr.notes.data.repositories

import com.gcalvocr.notes.data.datasources.LocalNoteDataSource
import com.gcalvocr.notes.data.mappers.NoteMapper.toLocalNote
import com.gcalvocr.notes.data.mappers.NoteMapper.toNote
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository

class NoteRepositoryImpl(
    private val localNoteDataSources: LocalNoteDataSource
): NoteRepository {

    override fun getAllNotes(): List<NoteModel> {
        return localNoteDataSources.getAllNotes().map{ item -> item.toNote()}
    }

    override fun addNote(note: NoteModel): NoteModel? {
        return localNoteDataSources.addNote(note.toLocalNote())!!.toNote()

    }

    override fun deleteNote(id: Long): Boolean {
        return localNoteDataSources.deleteNote(id)
    }

    override fun updateNote(note: NoteModel) {
        localNoteDataSources.updateNote(note.toLocalNote())
    }

}