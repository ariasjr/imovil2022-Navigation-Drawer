package es.imovil.fcrtrainer.ui.networks.networklayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentNetworkLayerBinding
import java.util.*
import kotlin.random.Random

class NetworkLayerFragment : Fragment() {

    private var _binding: FragmentNetworkLayerBinding?=null
    private val binding get()=_binding!!
    //private lateinit var arrayQuestions: Array<String>
    //private lateinit var arrayAnswers: Array<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val homeViewModel = ViewModelProvider(this).get(NetworkLayerViewModel::class.java)

        // Inflate the layout for this fragment
        _binding= FragmentNetworkLayerBinding.inflate(inflater, container, false)
        val root: View =binding.root

        val textView: TextView = binding.textlayer
        homeViewModel.text.observe(viewLifecycleOwner){
            textView.text = it
        }

        /*
        val rbApplication: RadioButton = binding.applicationLayer
        val rbTransport: RadioButton = binding.transportLayer
        val rbInternet: RadioButton = binding.internetLayer
        val rbLink: RadioButton = binding.linkLayer
        val rbGroup: RadioGroup = binding.radioGroup
        val textViewLayer: TextView = binding.textlayer
*/
        //arrays
        //arrayQuestions= resources.getStringArray(R.array.network_layer_questions)
        //arrayAnswers=resources.getStringArray(R.array.network_layer_answers)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}