package es.imovil.fcrtrainer.ui.codes.num_hosts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumHostsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Num_Hosts Fragment"
    }
    val text: LiveData<String> = _text
}