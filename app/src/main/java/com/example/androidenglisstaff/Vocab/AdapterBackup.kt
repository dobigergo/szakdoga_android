package com.example.androidenglisstaff.Vocab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidenglisstaff.R
import com.example.androidenglisstaff.model.Word

class VocabAdapterBackup : RecyclerView.Adapter<VocabAdapterBackup.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val hun_word: TextView = itemView.findViewById(R.id.hun_word)
        val eng_word: TextView = itemView.findViewById(R.id.eng_word)
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.text_item_view, parent, false)
                return ViewHolder(view)
            }
        }
    }

    var data = listOf<Word>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    private fun ViewHolder.bind(item: Word) {
        val res = itemView.context.resources
        eng_word.text = item.word1
        hun_word.text = item.word2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



}