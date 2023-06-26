package com.gcalvocr.notes.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<NavigationScreen>()
    val navigationEvent: LiveData<NavigationScreen>
        get() = _navigationEvent

    fun navigateTo(screen: NavigationScreen) {
        Log.i("Info", "Navigating to ${screen.name}")
        _navigationEvent.value = screen
    }
}

enum class NavigationScreen {
    AddNotes,
    TagList
}