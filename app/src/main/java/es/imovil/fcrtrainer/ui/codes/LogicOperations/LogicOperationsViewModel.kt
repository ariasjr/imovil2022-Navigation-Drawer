package es.imovil.fcrtrainer.ui.codes.LogicOperations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogicOperationsViewModel : ViewModel() {

    var num1:String=""
    var num2:String=""
    var answer:String=""
    var solution:String=""
    var operation:String=""

    private val _text = MutableLiveData<String>().apply {
        value = "This is Logic Operations Fragment"
    }
    val text: LiveData<String> = _text
}