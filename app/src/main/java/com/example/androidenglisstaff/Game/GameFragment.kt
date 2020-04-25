package com.example.androidenglisstaff.Game

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.androidenglisstaff.R
import com.example.androidenglisstaff.databinding.GameFragmentBinding
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment(){

    private lateinit var binding: GameFragmentBinding

    private lateinit var viewModel: GameViewModel

    private lateinit var viewModelFactory: GameViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment,container,false)

        viewModelFactory = GameViewModelFactory(GameFragmentArgs.fromBundle(arguments!!).topicName)

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(GameViewModel::class.java)

        binding.submitButton.setOnClickListener{onSubmit()}
        binding.gameLayout.setOnClickListener{onLoseFocus()}
        binding.button5.setOnClickListener{onEndGame()}

        viewModel.scoreString.observe(this, Observer { newScore ->
        binding.scoreText.text = newScore})
        viewModel.word.observe(this, Observer { newWord ->
            binding.word = newWord
        })
        viewModel.eventGameFinish.observe(this, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })
        viewModel.success.observe(this, Observer<Boolean>{hasFinishedLoading ->
            if(hasFinishedLoading) startGame()
        })

        binding.wordText.setBackgroundColor(Color.GRAY)
        return binding.root
    }

    private fun startGame() {
        viewModel.start()
        binding.animationView.visibility = View.INVISIBLE
        binding.gameLayout.visibility = View.VISIBLE
        binding.animationView.pauseAnimation()
    }

    private fun onLoseFocus() {
        binding.wordText.clearFocus()
    }

    private fun onSubmit() {
        if(!word_text.text.toString().equals("")){
            viewModel.onSubmit(word_text.text.toString())
            binding.wordText.setText("")
        }
    }

    fun onEndGame(){
        gameFinished()
    }

    fun gameFinished(){
        var score: String = viewModel.scoreString.value?:"0"
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToEndGameFragment(score))
        viewModel.onGameFinishComplete()
    }


}