package es.imovil.fcrtrainer.ui.codes.binario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BinarioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Binario Fragment"
    }
    val text: LiveData<String> = _text
}