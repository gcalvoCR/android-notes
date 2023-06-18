package com.gcalvocr.notes.data.repositories

import com.gcalvocr.notes.data.datasources.LocalNoteDataSources
import com.gcalvocr.notes.data.mappers.NoteMapper.toLocalNote
import com.gcalvocr.notes.data.mappers.NoteMapper.toNote
import com.gcalvocr.notes.data.models.LocalNote
import com.gcalvocr.notes.data.models.LocalTag
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.domain.repositories.NoteRepository
import java.text.DateFormat
import java.util.TimeZone

class NoteRepositoryImpl(
    private val localNoteDataSources: LocalNoteDataSources
): NoteRepository {

    override fun getAllNotes(): List<NoteModel> {
        return localNoteDataSources.getAllNotes().map{ item -> item.toNote()}
    }

    override fun addNote(title: String, description: String, tag: String): NoteModel? {
        // Tag logic
        var localTag = localNoteDataSources.getTag(tag)
        if (localTag == null ) {
            localTag = LocalTag(
                id= localNoteDataSources.getTagNewId(),
                title = tag
            )
        }
        // Date

        var note = LocalNote(
            id = localNoteDataSources.getNoteNewId(),
            title = title,
            description = description,
            tag = localTag,
            date = 1
        )

        val response = localNoteDataSources.addNote(note)
        if (response != null) {
            return response.toNote()
        }
        return null
    }

    override fun deleteNote(id: Int): Boolean {
        return localNoteDataSources.deleteNote(id)
    }

    override fun updateNote(note: NoteModel) {
        localNoteDataSources.updateNote(note.toLocalNote())
    }

}