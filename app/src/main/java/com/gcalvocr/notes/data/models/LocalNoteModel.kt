package com.gcalvocr.notes.data.models

// Esto es porque voy a obtener los datos de memoria

// Los Dataclass tienen un metodo equals implementado
data class LocalNoteModel(
    val id:Long,
    val title:String,
    val description:String?,
    val tag: LocalTagModel?,
    val date: Long
)
