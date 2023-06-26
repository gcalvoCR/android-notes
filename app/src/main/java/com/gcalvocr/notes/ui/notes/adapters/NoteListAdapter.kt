package com.gcalvocr.notes.ui.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcalvocr.notes.R
import com.gcalvocr.notes.domain.models.NoteModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView

class NoteListAdapter(
    private val onItemLongClicked: (noteModel: NoteModel) -> Unit
): RecyclerView.Adapter<NoteItemViewHolder>() {

    private val data = mutableListOf<NoteModel>()

    fun setData(dataSource: List<NoteModel>){
        data.clear()
        data.addAll(dataSource)
        notifyDataSetChanged()
    }

    fun removeData(id: Long){
        val index = data.indexOfFirst { item -> item.id == id }
        data.removeAt(index)
        notifyDataSetChanged()
    }

    fun addData(note: NoteModel){
        data.add(0, note)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout, parent, false)
        return NoteItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(data[position], onItemLongClicked)
    }

}

class NoteItemViewHolder(view: View):RecyclerView.ViewHolder(view){

    fun bind(noteModel: NoteModel,  onItemLongClicked: (noteModel: NoteModel) -> Unit){
        with(itemView){
            findViewById<MaterialTextView>(R.id.note_item_title).text = noteModel.title
            findViewById<MaterialTextView>(R.id.note_item_description).text = noteModel.description
            findViewById<MaterialTextView>(R.id.note_tag).text = "#${noteModel.tag.title}"
            findViewById<FloatingActionButton>(R.id.btn_delete_note).setOnLongClickListener {
                onItemLongClicked.invoke(noteModel)
                return@setOnLongClickListener false
            }
        }
    }
}