package com.gcalvocr.notes.ui.tags.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gcalvocr.notes.R
import com.gcalvocr.notes.data.repositories.TagRepositoryImpl
import com.gcalvocr.notes.data.datasources.LocalTagDataSource
import com.gcalvocr.notes.domain.models.TagModel
import com.gcalvocr.notes.domain.usecases.tags.GetTagsUseCase
import com.gcalvocr.notes.ui.main.viewmodels.MainViewModel
import com.gcalvocr.notes.ui.main.viewmodels.NavigationScreen
import com.gcalvocr.notes.ui.tags.adapters.TagListAdapter
import com.gcalvocr.notes.ui.tags.viewmodels.TagListViewModel
import com.gcalvocr.notes.ui.tags.viewmodels.factories.TagListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TagListFragment : Fragment() {

    // This is done to send data from the taglistfragment to the nodelistFragment
    companion object {
        const val TAG_ADDED_REQUEST_KEY = "TAG_ADDED_REQUEST_KEY"
    }


    // This dependencies will be eventually injected
    private val repository by lazy { TagRepositoryImpl(LocalTagDataSource) }
    private val getTagsUseCase by lazy { GetTagsUseCase(repository) }


    // view models
    private val viewModelFactory by lazy {
        TagListViewModelFactory(
            getTagsUseCase,
        )
    }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var  viewModel: TagListViewModel // What controls the data of this fragment


    // RecyclerView Objects
    private val adapter by lazy { TagListAdapter(::onTagSelected) }
    private lateinit var tagsRecyclerView: RecyclerView

    // Other UI elements
    private lateinit var fabAddAction: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_tag_list, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[TagListViewModel::class.java]
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        initViews(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun initViews(view: View) {
        with(view) {
            // Recycler View
            tagsRecyclerView = findViewById(R.id.tag_list)
            tagsRecyclerView.adapter = adapter
            tagsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            // Floating button
            fabAddAction = findViewById(R.id.fab_add_tag)
            fabAddAction.visibility = View.GONE
            fabAddAction.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun observe() {
        viewModel.tagListLiveData.observe(viewLifecycleOwner) { data ->
            tagsRecyclerView.post {
                adapter.setData(data)
            }
        }
        viewModel.selectedData.observe(viewLifecycleOwner, ::tagSaved)
    }

    private fun onTagSelected(tag: TagModel){
        Log.i("Info", tag.toString())
        viewModel.setSelected(tag)
        fabAddAction.visibility = View.VISIBLE
    }

    private fun tagSaved(tag: TagModel) {
        setFragmentResult(TAG_ADDED_REQUEST_KEY, bundleOf(TAG_ADDED_REQUEST_KEY to tag))
    }

}