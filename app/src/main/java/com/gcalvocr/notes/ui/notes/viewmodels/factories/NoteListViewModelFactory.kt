package com.gcalvocr.notes.ui.notes.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gcalvocr.notes.domain.usecases.notes.AddNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.DeleteNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.GetNotesUseCase
import com.gcalvocr.notes.ui.notes.viewmodels.NoteListViewModel

@Suppress("UNCHECKED_CAST")
class NoteListViewModelFactory(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase,
    private val addNotesUseCase: AddNotesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NoteListViewModel(
            getNotesUseCase = getNotesUseCase,
            deleteNotesUseCase = deleteNotesUseCase,
            addNotesUseCase = addNotesUseCase,
        ) as T
}