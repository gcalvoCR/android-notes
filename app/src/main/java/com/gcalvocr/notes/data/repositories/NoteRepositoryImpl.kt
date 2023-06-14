package com.gcalvocr.notes.data.repositories

import com.gcalvocr.notes.data.datasources.LocalNoteDataSources
import com.gcalvocr.notes.data.mappers.NoteMapper.toNote
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository

class NoteRepositoryImpl(
    private val localNoteDataSources: LocalNoteDataSources
): NoteRepository {

    override fun getAllNotes(): List<NoteModel> {
        return localNoteDataSources.getAllNotes().map{ item -> item.toNote()}
    }


}