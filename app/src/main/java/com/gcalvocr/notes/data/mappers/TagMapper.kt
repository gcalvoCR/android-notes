package com.gcalvocr.notes.data.mappers

import com.gcalvocr.notes.data.models.LocalTagModel
import com.gcalvocr.notes.domain.models.TagModel

object TagMapper {

    fun LocalTagModel.toTag(): TagModel  = TagModel(
        id = this.id,
        title = this.title
    )

    fun TagModel.toLocalTag(): LocalTagModel  = LocalTagModel(
        id = this.id,
        title = this.title
    )
}