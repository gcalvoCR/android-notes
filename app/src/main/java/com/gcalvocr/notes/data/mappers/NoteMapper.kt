package com.gcalvocr.notes.data.mappers

import com.gcalvocr.notes.data.mappers.TagMapper.toTag
import com.gcalvocr.notes.data.models.LocalNote
import com.gcalvocr.notes.domain.models.NoteModel

// Se encarga de maper Localnotes con Note
object NoteMapper {

    fun LocalNote.toNote(): NoteModel {
        return NoteModel(
            id = this.id,
            title = this.title,
            description = this.description,
            tag = this.tag.toTag(),
            date = this.date
        )

    }
}