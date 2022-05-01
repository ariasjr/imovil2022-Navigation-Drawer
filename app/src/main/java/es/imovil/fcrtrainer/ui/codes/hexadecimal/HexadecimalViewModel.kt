package es.imovil.fcrtrainer.ui.codes.hexadecimal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HexadecimalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hexadecimal Fragment"
    }
    val text: LiveData<String> = _text
}