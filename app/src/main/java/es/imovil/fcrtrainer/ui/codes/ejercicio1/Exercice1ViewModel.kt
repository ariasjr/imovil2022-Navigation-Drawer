package es.imovil.fcrtrainer.ui.codes.ejercicio1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Exercice1ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is exercice1 Fragment"
    }
    val text: LiveData<String> = _text
}