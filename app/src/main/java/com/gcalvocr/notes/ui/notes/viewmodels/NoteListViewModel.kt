package com.gcalvocr.notes.ui.notes.viewmodels

import android.util.Log
import android.widget.Toast
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

class NoteListViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase,
    private val addNotesUseCase: AddNotesUseCase,

): ViewModel() {

    // property to control if the dialog is shown or not
    private var _isDialogShown = MutableLiveData<Boolean>()
    val isDialogShown: LiveData<Boolean>
        get() = _isDialogShown

    // property to control the tag selected on another fragment
    private val _tagAddedLiveData = MutableLiveData<TagModel>()
    val tagAddedLiveData: LiveData<TagModel>
        get() = _tagAddedLiveData


    // property to control the tag selected on another fragment
    private val _noteModelLiveData = MutableLiveData<NoteModel>()
    val noteModelLiveData: LiveData<NoteModel>
        get() = _noteModelLiveData


    // List of Notes
    private val _noteListLiveData = MutableLiveData<List<NoteModel>>()
    val noteListLiveData: LiveData<List<NoteModel>>
        get() = _noteListLiveData

    fun onViewReady(){
        val list = getNotesUseCase.execute()
        _noteListLiveData.value = list
    }

    fun setTag(newTagModel: TagModel) {
        _tagAddedLiveData.value = newTagModel
    }

    fun setNote(note: NoteModel){
        _noteModelLiveData.value=note
    }

    fun clearNote(){
        val note = NoteModel(
            id = 0,
            title = "",
            description = "",
            tag = TagModel(0, ""),
            date = System.currentTimeMillis()
        )
        _noteModelLiveData.value = note
    }

    fun onDeleteLongClicked(id: Long): Boolean {
        return deleteNotesUseCase.execute(id)
    }

    fun onAddNoteClicked(title: String, description: String): NoteModel? {

        val note = NoteModel(
            id = 0,
            title = title,
            description = description,
            tag =  _tagAddedLiveData.value!!,
            date = System.currentTimeMillis()
        )

        return addNotesUseCase.execute(note)
    }

    fun onShowDialog(value: Boolean){
        _isDialogShown.value = value
    }

    fun onRemoveItem(note: NoteModel) {
        deleteNotesUseCase.execute(note.id)
        val list = _noteListLiveData.value?.filter { it.id != note.id } ?: return
        _noteListLiveData.value = list
    }

    //fun confirmRemoveItem(note: NoteModel){
    //    Log.i("Action", note.toString())
    //    _showDeleteMessageLiveData.value = note
    //}

}