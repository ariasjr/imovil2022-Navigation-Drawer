package es.imovil.fcrtrainer.ui.highscores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HighscoreViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Highscore Fragment"
    }
    val text: LiveData<String> = _text

    private val _exercises:MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    val exercises:LiveData<MutableList<String>> = _exercises

    fun addExerciseToList(exercise:String) {
        _exercises.value?.add(exercise)
        // Notify oberservers
        _exercises.value = _exercises.value
    }

    fun addExercisesToList(exs:List<String>) {
        _exercises.value?.addAll(exs)
        // Notify observers
        _exercises.value = _exercises.value
    }

    fun clearList() {
        _exercises.value = mutableListOf()
    }

    private val _scores:MutableLiveData<List<Pair<String, Int>>> = MutableLiveData(listOf())
    val scores:LiveData<List<Pair<String, Int>>> = _scores

    fun updateScores(sc:List<Pair<String,Int>>) {
        _scores.value =  sc
    }


}