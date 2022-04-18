package es.imovil.fcrtrainer.ui.codes.signed_magnitude

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignedMagnitudeViewModel : ViewModel() {

    var number:String = ""
    var answer:String = ""
    var solution:String = ""

    private val _text = MutableLiveData<String>().apply {
        value = "This is Signed Magnitude Fragment"
    }
    val text: LiveData<String> = _text
}