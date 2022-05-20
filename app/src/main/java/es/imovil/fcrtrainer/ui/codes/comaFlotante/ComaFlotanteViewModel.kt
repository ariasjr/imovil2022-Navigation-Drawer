package es.imovil.fcrtrainer.ui.codes.comaFlotante

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComaFlotanteViewModel :ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Coma Flotante Fragment"
    }
    val text: LiveData<String> = _text
}