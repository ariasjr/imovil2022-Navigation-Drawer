package es.imovil.fcrtrainer.ui.codes.ejercicio2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Exercice2ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is exercice2 Fragment"
    }
    val text: LiveData<String> = _text
}