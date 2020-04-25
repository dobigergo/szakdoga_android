package com.example.androidenglisstaff.Topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidenglisstaff.databinding.TopicItemViewBinding
import com.example.androidenglisstaff.model.Topic

class TopicAdapter(val clickListener: TopicListener) : ListAdapter<Topic, TopicAdapter.ViewHolder>(TopicDiffCallback()){

    class ViewHolder private constructor(val binding: TopicItemViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Topic,
            clickListener: TopicListener
        ){
            binding.topic = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }



        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopicItemViewBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }


}

class TopicDiffCallback: DiffUtil.ItemCallback<Topic>(){
    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
       return oldItem==newItem
    }

}

class TopicListener(val clickListener: (topicId: Topic) -> Unit){
    fun onClick(topic: Topic) = clickListener(topic)
}