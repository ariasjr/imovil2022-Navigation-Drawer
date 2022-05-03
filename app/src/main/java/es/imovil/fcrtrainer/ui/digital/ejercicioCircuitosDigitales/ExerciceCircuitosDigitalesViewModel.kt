package es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.imovil.fcrtrainer.R

class ExerciceCircuitosDigitalesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Introduce la salida S del circuito digital"
    }
    val text: LiveData<String> = _text
}