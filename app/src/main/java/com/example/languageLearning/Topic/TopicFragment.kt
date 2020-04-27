package com.example.languageLearning.Topic


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.languageLearning.R
import com.example.languageLearning.databinding.TopicFragmentBinding


class TopicFragment : Fragment() {

    private lateinit var binding: TopicFragmentBinding

    private lateinit var viewModel: TopicViewModel

    val adapter = TopicAdapter(TopicListener { topic -> viewModel.setTopic(topic.name)})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.topic_fragment,container,false)
        binding.topicRV.adapter = adapter
        binding.vocabButton.isEnabled = false
        binding.startButton.isEnabled = false
        viewModel = ViewModelProviders.of(this).get(TopicViewModel::class.java)

        viewModel.success.observe(viewLifecycleOwner, Observer{hasFinished -> if(hasFinished){
            setList()
        } })

        viewModel.isTopicSelected.observe(viewLifecycleOwner, Observer{topicSelected -> if(topicSelected){
            binding.startButton.isEnabled=true
            binding.vocabButton.isEnabled=true
        } })

        viewModel.topicList.observe(viewLifecycleOwner, Observer { it?.let{
            adapter.submitList(it)
        } })

        binding.vocabButton.setOnClickListener{
            findNavController().navigate(TopicFragmentDirections.actionTopicFragmentToVocabFragment(viewModel.selectedTopic.value!!))
        }

        binding.startButton.setOnClickListener{
            findNavController().navigate(TopicFragmentDirections.actionTopicFragmentToGameFragment(viewModel.selectedTopic.value!!))
        }

        return binding.root
        }

        fun setList() {
            viewModel.setList()
        }
}

