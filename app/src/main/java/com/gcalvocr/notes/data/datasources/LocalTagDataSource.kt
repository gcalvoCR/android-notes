package com.gcalvocr.notes.data.datasources

import android.util.Log
import com.gcalvocr.notes.data.models.LocalTagModel

object LocalTagDataSource {

    private val tags = mutableListOf(
        LocalTagModel(
            id = 1,
            title = "Trabajo",
        ),
        LocalTagModel(
            id = 2,
            title = "Hogar",
        ),
    )

    fun getTags(): List<LocalTagModel> = tags

    fun addTag(tag: LocalTagModel): LocalTagModel {
        tags.add(tag)
        return tag
    }

    fun getTag(id: Long): LocalTagModel? {
        try {
            val tag = tags.first { item -> item.id == id }
            return tag
        } catch (e: NoSuchElementException) {
            return null
        }
    }

    fun removeTag(id: Long) {
        tags.removeIf { tag -> tag.id == id }
    }

    fun editTag(tag: LocalTagModel){
        val index = tags.indexOfFirst { item -> item.id == tag.id }
        tags[index] = tag
    }

    fun getTagNewId(): Long {
        // Para este ejercicio sabemos que el indice es incremental
        val newId = tags[tags.size -1].id + 1
        Log.i("Action", "New Note index $newId")
        return newId
    }

    fun getTag(text: String): LocalTagModel? {
        for (tag in tags){
            if (tag.title == text) {
                return tag
            }
        }
        return null
    }
}