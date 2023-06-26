package com.gcalvocr.notes.ui.tags.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gcalvocr.notes.data.datasources.LocalNoteDataSource
import com.gcalvocr.notes.data.repositories.NoteRepositoryImpl
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.domain.repositories.NoteRepository
import com.gcalvocr.notes.domain.usecases.notes.AddNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.DeleteNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.GetNotesUseCase
import com.gcalvocr.notes.domain.usecases.tags.GetTagsUseCase

class TagListViewModel(
    private val getTagsUseCase: GetTagsUseCase

): ViewModel() {

    // Logic to get selected data
    private val _selectedData = MutableLiveData<TagModel>()
    val selectedData: LiveData<TagModel>
        get() = _selectedData

    private val _tagListLiveData = MutableLiveData<List<TagModel>>()
    val tagListLiveData: LiveData<List<TagModel>>
        get() = _tagListLiveData

    init {
        getTags()
    }

    private fun getTags() {
        _tagListLiveData.value = getTagsUseCase.execute()
    }

    fun setSelected(tag: TagModel){
        _selectedData.value = tag
    }

}