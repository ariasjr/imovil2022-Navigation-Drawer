package es.imovil.fcrtrainer.ui.digital.ejercicio4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciceNViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is exercice N Fragment"
    }
    val text: LiveData<String> = _text
}