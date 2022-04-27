package es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExerciceCircuitosDigitalesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is exercice of Digital Circuits Fragment"
    }
    val text: LiveData<String> = _text
}