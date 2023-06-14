package com.gcalvocr.notes.data.models

// Esto es porque voy a obtener los datos de memoria

// Los Dataclass tienen un metodo equals implementado
data class LocalNote(
    val id:Int,
    val title:String,
    val description:String?,
    val tag: LocalTag,
    val date: Int
)
