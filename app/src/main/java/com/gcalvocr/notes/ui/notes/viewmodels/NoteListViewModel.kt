package com.gcalvocr.notes.ui.notes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gcalvocr.notes.data.datasources.LocalNoteDataSources
import com.gcalvocr.notes.data.repositories.NoteRepositoryImpl
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.repositories.NoteRepository
import com.gcalvocr.notes.domain.usecases.GetNotesUseCase

class NoteListViewModel: ViewModel() {

    private val dataSource = LocalNoteDataSources()
    private val repository: NoteRepository = NoteRepositoryImpl(dataSource)
    private val getNotesUseCase = GetNotesUseCase(repository)

    private val _noteListLiveData = MutableLiveData<List<NoteModel>>()
    val noteListLiveData: LiveData<List<NoteModel>>
        get() = _noteListLiveData

    fun onViewReady(){
        val list = getNotesUseCase.execute()
        _noteListLiveData.value = list
    }

}