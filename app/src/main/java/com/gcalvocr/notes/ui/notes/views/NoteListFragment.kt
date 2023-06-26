package com.gcalvocr.notes.ui.notes.views

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcalvocr.notes.R
import com.gcalvocr.notes.data.datasources.LocalNoteDataSource
import com.gcalvocr.notes.data.repositories.NoteRepositoryImpl
import com.gcalvocr.notes.domain.models.NoteModel
import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.domain.usecases.notes.AddNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.DeleteNotesUseCase
import com.gcalvocr.notes.domain.usecases.notes.GetNotesUseCase
import com.gcalvocr.notes.ui.main.viewmodels.MainViewModel
import com.gcalvocr.notes.ui.main.viewmodels.NavigationScreen
import com.gcalvocr.notes.ui.notes.adapters.NoteListAdapter
import com.gcalvocr.notes.ui.notes.viewmodels.NoteListViewModel
import com.gcalvocr.notes.ui.notes.viewmodels.factories.NoteListViewModelFactory
import com.gcalvocr.notes.ui.tags.views.TagListFragment.Companion.TAG_ADDED_REQUEST_KEY
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteListFragment : Fragment() {


    // This dependencies will be eventually injected
    private val repository by lazy {NoteRepositoryImpl(LocalNoteDataSource)}

    private val getNoteListUseCase by lazy { GetNotesUseCase(repository) }
    private val deleteNotesUseCase by lazy { DeleteNotesUseCase(repository) }
    private val addNoteUseCase by lazy { AddNotesUseCase(repository) }


    // view models
    private val viewModelFactory by lazy {
        NoteListViewModelFactory(
            getNoteListUseCase,
            deleteNotesUseCase,
            addNoteUseCase
        )
    }
    private lateinit var mainViewModel: MainViewModel // to navigate to other screens
    private lateinit var  viewModel: NoteListViewModel // What controls the data of this fragment

    // Fragment components
    private lateinit var notesRecyclerView: RecyclerView

    // Dialog components
    private lateinit var btnAddMain: FloatingActionButton
    private lateinit var editNote: EditText
    private lateinit var editDescription: EditText
    private lateinit var tagText: TextView
    private lateinit var btnSelectTag: ImageView
    private lateinit var btnAddDialog: Button


    private val adapter by lazy {
        NoteListAdapter(
            onItemLongClicked = { item -> onListItemClicked(item) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(TAG_ADDED_REQUEST_KEY) { requestKey, bundle ->
            val tag = bundle.getParcelable<TagModel>(requestKey) ?: return@setFragmentResultListener
            viewModel.setTag(tag)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteListViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        initViews(view)
        initListeners()
        observe()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewReady()
    }

    private fun initViews(view: View){
        with(view){
            notesRecyclerView = findViewById(R.id.notes_list)
            notesRecyclerView.adapter = adapter
            notesRecyclerView.layoutManager= LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            btnAddMain = findViewById(R.id.btn_add_main)
            // btnAddMain.setOnClickListener{ mainViewModel.navigateTo(NavigationScreen.TagList)}
        }
    }

    private fun observe(){
        viewModel.noteListLiveData.observe(viewLifecycleOwner){list ->
            adapter.setData(list)
        }
        if (viewModel.isDialogShown.value == true) {
            showDialog()
        }
        // viewModel.showDeleteMessageLiveData.observe(viewLifecycleOwner, ::showRemoveConfirmMessage)
    }

    private fun onListItemClicked(note: NoteModel) {
        //viewModel.confirmRemoveItem(note)
        showRemoveConfirmMessage(note)
    }

    fun addNote(title: String, description: String) {
        val note = viewModel.onAddNoteClicked(title, description)
        if (note != null) {
            adapter.addData(note)
            Toast.makeText(context, "Nota ${note.title} añadida", Toast.LENGTH_LONG).show()
            viewModel.clearNote()
        }
    }

    private fun initListeners() {
        btnAddMain.setOnClickListener{ showDialog() }
    }

    private fun showDialog() {
        viewModel.onShowDialog(true)
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.add_note_dialog_layout)
        dialog.show()
        editNote = dialog.findViewById(R.id.edit_note)
        editDescription = dialog.findViewById(R.id.edit_description)
        tagText = dialog.findViewById(R.id.tv_tag_name)
        btnSelectTag = dialog.findViewById(R.id.iv_add_tag_chevron)
        btnAddDialog = dialog.findViewById(R.id.btn_dialog_add)
        btnAddDialog.setOnClickListener {
            onAdd()
            dialog.hide()
            viewModel.onShowDialog(false)
        }
        btnSelectTag.setOnClickListener{
            val note = NoteModel(
                id = 0,
                title = editNote.text.toString(),
                description = editDescription.text.toString(),
                tag = TagModel(0, ""),
                date = System.currentTimeMillis()
            )
            viewModel.setNote(note)

            dialog.hide()
            mainViewModel.navigateTo(NavigationScreen.TagList)
        }
        viewModel.tagAddedLiveData.observe(viewLifecycleOwner){tag ->
            tagText.text = tag.title
        }

        viewModel.noteModelLiveData.observe(viewLifecycleOwner){note ->
            editNote.setText(note.title)
            editDescription.setText(note.description)
        }

    }

    private fun onAdd(){
        val title = editNote.text.toString()
        val description = editDescription.text.toString()
        addNote(title, description)
    }

    private fun showRemoveConfirmMessage(note: NoteModel) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.remove_note_confirm_title)
            .setMessage(getString(R.string.remove_tag_confirm_message, note.title))
            .setPositiveButton(R.string.confirm_alert_positive_action) { _, _ ->
                viewModel.onRemoveItem(note)
                // val response = viewModel.onDeleteLongClicked(note.id)
                // if (response) {
                //    adapter.removeData(note.id)
                //    Toast.makeText(context, "${note.title} fue eliminado", Toast.LENGTH_LONG).show()
                //}
            }
            .setNegativeButton(R.string.confirm_alert_negative_action, null)
            .show()
    }

}