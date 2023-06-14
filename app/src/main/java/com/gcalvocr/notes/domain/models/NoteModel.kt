package com.gcalvocr.notes.domain.models

// nullable se representa con ?
data class NoteModel(
    val id:Int,
    val title:String,
    val description:String?,
    val tag:TagModel,
    val date: Int
)
