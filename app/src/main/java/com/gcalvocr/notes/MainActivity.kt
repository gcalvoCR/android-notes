package com.gcalvocr.notes

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.ui.notes.NoteListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date

class MainActivity : AppCompatActivity() {

    // Fragment
    lateinit var noteList: NoteListFragment

    // Dialog components
    lateinit var btnAddMain: FloatingActionButton
    lateinit var editNote: EditText
    lateinit var editDescription: EditText
    lateinit var editTag: EditText
    lateinit var btnAddDialog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteList = NoteListFragment()
        navigateToFragment(noteList)
        initComponents()
        initListeners()
    }

    private fun navigateToFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun initListeners() {
        btnAddMain.setOnClickListener{ showDialog() }
    }

    private fun initComponents() {
        btnAddMain = findViewById(R.id.btn_add_main)
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.show()
        editNote = dialog.findViewById(R.id.edit_note)
        editDescription = dialog.findViewById(R.id.edit_description)
        editTag = dialog.findViewById(R.id.edit_tag)
        btnAddDialog = dialog.findViewById(R.id.btn_dialog_add)
        btnAddDialog.setOnClickListener {
            onAdd()
            dialog.hide()
        }
    }

    private fun onAdd(){
        val title = editNote.text.toString()
        val description = editDescription.text.toString()
        val tag = editTag.text.toString()
        noteList.addNote(title, description, tag)
    }

}