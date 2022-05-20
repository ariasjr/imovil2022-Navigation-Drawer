package es.imovil.fcrtrainer.ui.digital.logicgate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogicgateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is logicgate Fragment"
    }
    val text: LiveData<String> = _text
}