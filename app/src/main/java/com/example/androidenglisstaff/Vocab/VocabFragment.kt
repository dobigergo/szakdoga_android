package com.example.androidenglisstaff.Vocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidenglisstaff.R
import com.example.androidenglisstaff.databinding.VocabFragmentBinding

class VocabFragment : Fragment() {

    private lateinit var binding: VocabFragmentBinding

    private lateinit var viewModel: VocabLoaderViewModel

    private lateinit var viewModelFactory: VocabLoaderViewModelFactory

    val adapter = VocabAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.vocab_fragment,container,false)
        binding.vocabRV.adapter = adapter

        viewModelFactory = VocabLoaderViewModelFactory(VocabFragmentArgs.fromBundle(arguments!!).topicName)

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(VocabLoaderViewModel::class.java)


        viewModel.success.observe(viewLifecycleOwner, Observer { hasFinished -> if(hasFinished){getList()} })

        viewModel.wordList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

    private fun getList() {
        viewModel.setList()
    }

}