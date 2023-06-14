package com.gcalvocr.notes.data.mappers

import com.gcalvocr.notes.data.models.LocalTag
import com.gcalvocr.notes.domain.models.TagModel

object TagMapper {

    fun LocalTag.toTag(): TagModel  = TagModel(
        id = this.id,
        title = this.title
    )
}