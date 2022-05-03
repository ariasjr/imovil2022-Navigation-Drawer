package es.imovil.fcrtrainer.ui.networks.networklayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkLayerViewModel: ViewModel() {

    private val _text=MutableLiveData<String>().apply {
        value="This is NetworkLayer Fragment"
    }

    val text: LiveData<String> =_text
}