package com.example.languageLearning.Title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.languageLearning.R
import com.example.languageLearning.databinding.TitleFragmentBinding

class TitleFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View {

        val binding: TitleFragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.title_fragment,container,false)

        binding.button2.isEnabled = false
        binding.button3.isEnabled = false

        binding.buttonStart.setOnClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToTopicFragment())
        }
        binding.button2.setOnClickListener{
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToVocabFragment("culture"))
        }


        val application = requireNotNull(this.activity).application
        binding.setLifecycleOwner(this)


        return binding.root
    }
}