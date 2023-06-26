package com.gcalvocr.notes.ui.tags.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.gcalvocr.notes.R
import com.gcalvocr.notes.domain.models.TagModel

class TagListAdapter(
    private val onTagSelected: (tag: TagModel) -> Unit
) : RecyclerView.Adapter<TagListAdapter.TagViewHolder>() {

    private val data = mutableListOf<TagModel>()

    fun setData(dataSource: List<TagModel>) {
        data.clear()
        data.addAll(dataSource)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tag_item_layout, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(data[position], onTagSelected)
    }


    class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(tag: TagModel, onTagSelected: (uiTag: TagModel) -> Unit) {
            with(itemView) {
                with(itemView) {
                    findViewById<CheckBox>(R.id.cb_tag_isChecked).apply {
                        setOnClickListener {
                            onTagSelected(tag)
                        }
                    }
                    findViewById<TextView>(R.id.tv_tag_name).text = tag.title
                }
                itemView.setOnClickListener {
                    onTagSelected.invoke(tag)
                    return@setOnClickListener
                }
            }
        }
    }
}