package com.gcalvocr.notes.data.repositories

import com.gcalvocr.notes.data.datasources.LocalTagDataSource
import com.gcalvocr.notes.data.mappers.TagMapper.toLocalTag
import com.gcalvocr.notes.data.mappers.TagMapper.toTag
import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.domain.repositories.TagRepository

class TagRepositoryImpl(
    private val localtagDataSource: LocalTagDataSource
): TagRepository {

    override fun getAllTags(): List<TagModel> {
        return localtagDataSource.getTags().map{ item -> item.toTag()}
    }

    override fun addtag(tag: TagModel) {
        localtagDataSource.addTag(tag.toLocalTag())!!.toTag()
    }

    override fun deleteTag(id: Long) {
        localtagDataSource.removeTag(id)
    }

    override fun updateTag(tag: TagModel) {
        localtagDataSource.editTag(tag.toLocalTag())
    }

}