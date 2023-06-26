package com.gcalvocr.notes.domain.repositories

import com.gcalvocr.notes.domain.models.TagModel

interface TagRepository {
    fun getAllTags(): List<TagModel>
    fun addtag(tag: TagModel)
    fun deleteTag(id: Long)
    fun updateTag(tag: TagModel)
}