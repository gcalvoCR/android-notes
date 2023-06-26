package com.gcalvocr.notes.ui.tags.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcalvocr.notes.domain.usecases.notes.AddNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.DeleteNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.GetNotesUseCase
import com.gcalvocr.notes.domain.usecases.tags.GetTagsUseCase
import com.gcalvocr.notes.ui.notes.viewmodels.NoteListViewModel
import com.gcalvocr.notes.ui.tags.viewmodels.TagListViewModel

@Suppress("UNCHECKED_CAST")
class TagListViewModelFactory(
    private val getTagsUseCase: GetTagsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        TagListViewModel(
            getTagsUseCase = getTagsUseCase
        ) as T
}