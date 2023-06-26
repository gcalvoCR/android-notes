package com.gcalvocr.notes.data.datasources

import android.util.Log
import com.gcalvocr.notes.data.models.LocalNoteModel
import com.gcalvocr.notes.data.models.LocalTagModel

object LocalNoteDataSource {

    private var notes = mutableListOf(
        LocalNoteModel(
            id = 1,
            title = "Android Architecture",
            description = "Ensenar la estructura de de una aplicacion",
            tag = LocalTagDataSource.getTag(1),
            date = 1685660673
        ),
        LocalNoteModel(
            id = 2,
            title = "Android Database",
            description = "Ensenar la estructura de datos de una aplicacion",
            tag = LocalTagDataSource.getTag(1),
            date = 1685660673
        ),
        LocalNoteModel(
            id = 3,
            title = "Ordenar Cuarto",
            description = null,
            tag = LocalTagDataSource.getTag(2),
            date = 1685660673
        )
    )

    fun getAllNotes(): List<LocalNoteModel> = notes

    fun addNote(localNoteModel: LocalNoteModel): LocalNoteModel? {
        Log.i("Action", "Add new note")
        val response  = notes.add(localNoteModel)
        if (response) {
            Log.i("Action", "Note insertada exitosamente $localNoteModel")
            return localNoteModel
        }
        return null
    }

    fun deleteNote(id: Long): Boolean {
        Log.i("Action", "Delete note triggered, note id:${id}")
        Log.i("Action", "Notes $notes")
        return notes.removeIf{ note -> note.id == id}
    }

    fun updateNote(note: LocalNoteModel) {
        val index = notes.indexOfFirst { item-> item.id == note.id }
        notes[index] = note
    }

    fun getNoteNewId(): Long {
        // Para este ejercicio sabemos que el indice es incremental
        val newId = notes[notes.size -1].id + 1
        Log.i("Action", "New Note index $newId")
        return newId
    }

}