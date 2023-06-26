package com.gcalvocr.notes.ui.main.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gcalvocr.notes.R
import com.gcalvocr.notes.ui.tags.views.TagListFragment
import com.gcalvocr.notes.ui.main.viewmodels.MainViewModel
import com.gcalvocr.notes.ui.main.viewmodels.NavigationScreen
import com.gcalvocr.notes.ui.notes.views.NoteListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToFragment(NoteListFragment(), true)
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observe()
    }

    private fun observe() {
        viewModel.navigationEvent.observe(this) { event ->
            Log.i("Event", event.toString())
            when (event) {
                NavigationScreen.TagList -> navigateToFragment(TagListFragment())
                else -> Unit
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment, isInitialFragment: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (!isInitialFragment) {
            transaction.addToBackStack(fragment.javaClass.name)
        }
        transaction
            .commit()
    }
}