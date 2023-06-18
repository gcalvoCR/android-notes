package com.gcalvocr.notes.data.datasources

import android.util.Log
import com.gcalvocr.notes.data.models.LocalNote
import com.gcalvocr.notes.data.models.LocalTag

class LocalNoteDataSources {

    private var notes = mutableListOf(
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
        LocalNote(
            id = 5,
            title = "Retrofit 2",
            description = "Como consumir servicios Android en la libreria Retrofit",
            tag = LocalTag(
                id = 1,
                title =  "Cenfotec"
            ),
            date = 1685660673
        )
    )

    fun getAllNotes(): List<LocalNote> = notes

    fun addNote(localNote: LocalNote): LocalNote? {
        Log.i("Action", "Add new note")
        val response  = notes.add(localNote)
        if (response) {
            Log.i("Action", "Note insertada exitosamente $localNote")
            return localNote
        }
        return null
    }

    fun deleteNote(id: Int): Boolean {
        Log.i("Action", "Delete note triggered, note id:${id}")
        Log.i("Action", "Notes $notes")
        return notes.removeIf{ note -> note.id == id}
    }

    fun updateNote(note: LocalNote) {
        val index = notes.indexOfFirst { item-> item.id == note.id }
        notes[index] = note
    }

    fun getNoteNewId(): Int {
        // Para este ejercicio sabemos que el indice es incremental
        val newId = notes[notes.size -1].id + 1
        Log.i("Action", "New Note index $newId")
        return newId
    }

    fun getTag(text: String): LocalTag? {
        for (note in notes){
            if (note.tag.title == text) {
                Log.i("Action", "Tag encontrada")
                return note.tag
            }
        }
        Log.i("Action", "Tag NO encontrada")
        return null
    }

    fun getTagNewId(): Int {
        // Para este ejercicio podemos averiguarlo con esta pequeña logica
        var id = 0
        for (note in notes){
            if (note.tag.id > id){
                id = note.tag.id
            }
        }
        Log.i("Action", "New Tag id ${id + 1}")
        return id + 1
    }
}