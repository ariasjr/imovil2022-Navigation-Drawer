package es.imovil.fcrtrainer.ui.codes.twosComplement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwosComplementViewModel : ViewModel(){

    private val _text = MutableLiveData<String>().apply{
        value = "This is Twos Complement"
    }

    val text: LiveData<String>  = _text

}