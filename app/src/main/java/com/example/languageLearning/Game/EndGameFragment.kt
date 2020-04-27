package com.example.languageLearning.Game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.languageLearning.R
import com.example.languageLearning.databinding.EndGameFragmentBinding

class EndGameFragment : Fragment(){

    private lateinit var binding : EndGameFragmentBinding
    private lateinit var viewModel: EndGameViewModel
    private lateinit var viewModelFactory: EndGameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.end_game_fragment,container,false)
        binding.button4.setOnClickListener { findNavController().navigate(EndGameFragmentDirections.actionEndGameFragmentToTitleFragment()) }

        viewModelFactory = EndGameViewModelFactory(EndGameFragmentArgs.fromBundle(arguments!!).score)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(EndGameViewModel::class.java)

        viewModel.score.observe(this, Observer { newScore ->
            binding.scoreText.text = newScore
        })

        return binding.root
    }

}