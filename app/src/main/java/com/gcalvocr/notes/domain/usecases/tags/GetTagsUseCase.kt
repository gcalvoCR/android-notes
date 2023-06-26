package com.gcalvocr.notes.domain.usecases.tags

import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.domain.repositories.TagRepository

// Los usecases no deben de guardar datos, solo ejecutar
class GetTagsUseCase(
    private val tagRepository: TagRepository
)
{
    fun execute():List<TagModel> = tagRepository.getAllTags()
}