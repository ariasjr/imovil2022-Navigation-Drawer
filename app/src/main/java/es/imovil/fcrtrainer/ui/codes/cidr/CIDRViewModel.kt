package es.imovil.fcrtrainer.ui.codes.cidr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CIDRViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is CIDR Fragment"
    }
    val text: LiveData<String> = _text
}