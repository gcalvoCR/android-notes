package com.gcalvocr.notes.data.datasources

import com.gcalvocr.notes.data.models.LocalNote
import com.gcalvocr.notes.data.models.LocalTag

class LocalNoteDataSources {

    private val notes = mutableListOf(
        LocalNote(
            id = 1,
            title = "Android Architecture",
            description = "Ensenar la estructura de de una aplicacion",
            tag = LocalTag(
                id = 1,
                title =  "Cenfotec"
            ),
            date = 1685660673
        ),
        LocalNote(
            id = 2,
            title = "Android Database",
            description = "Ensenar la estructura de datos de una aplicacion",
            tag = LocalTag(
                id = 1,
                title =  "Cenfotec"
            ),
            date = 1685660673
        ),
        LocalNote(
            id = 3,
            title = "Ordenar Cuarto",
            description = null,
            tag = LocalTag(
                id = 2,
                title =  "Hogar"
            ),
            date = 1685660673
        ),
        LocalNote(
            id = 4,
            title = "Retrofit",
            description = "Como consumir servicios Android en la libreria Retrofit",
            tag = LocalTag(
                id = 1,
                title =  "Cenfotec"
            ),
            date = 1685660673
        ),
    )

    fun getAllNotes(): List<LocalNote> = notes

    fun addNote(localNote: LocalNote) {
        notes.add(localNote)
    }

    fun deleteNote(id: Int){
        notes.removeIf{ note -> note.id == id}
    }

    fun updateNote(note: LocalNote) {
        val index = notes.indexOfFirst { item-> item.id == note.id }
        notes[index] = note
    }
}