package es.imovil.fcrtrainer.ui.codes.NetworkMask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkMaskViewModel :ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is NetWorkMask Fragment"
    }
    val text: LiveData<String> = _text
}