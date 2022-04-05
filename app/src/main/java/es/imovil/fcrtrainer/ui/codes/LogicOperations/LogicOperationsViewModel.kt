package es.imovil.fcrtrainer.ui.codes.LogicOperations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogicOperationsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Logic Operations Fragment"
    }
    val text: LiveData<String> = _text
}