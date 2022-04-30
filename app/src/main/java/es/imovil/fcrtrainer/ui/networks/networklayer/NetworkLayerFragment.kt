package es.imovil.fcrtrainer.ui.networks.networklayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentNetworkLayerBinding
import java.util.*
import kotlin.random.Random

class NetworkLayerFragment : Fragment() {

    private var _binding: FragmentNetworkLayerBinding?=null
    private val binding get()=_binding!!

    //Arrays
    private lateinit var questions: Array<String>
    private lateinit var answers: Array<String>

    //Botton
    private lateinit var bCheck: Button
    private lateinit var bSolution: Button

    //RadioGroup
    private lateinit var rbApplication: RadioButton
    private lateinit var rbTransport: RadioButton
    private lateinit var rbInternet: RadioButton
    private lateinit var rbLink: RadioButton

    //Question
    private lateinit var tvQuestion:TextView
    private var nAnswer: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentNetworkLayerBinding.inflate(inflater, container, false)
        val root: View =binding.root

        bCheck=binding.bCheck
        bSolution=binding.bSolution

        rbApplication=binding.rbApplication
        rbTransport=binding.rbTransport
        rbInternet=binding.rbInternet
        rbLink=binding.rbLink
        tvQuestion=binding.tvQuestion

        questions= resources.getStringArray(R.array.network_layer_questions)
        answers=resources.getStringArray(R.array.network_layer_answers)


        val mRandomGenerator= Random.nextInt(11)
        tvQuestion.text=resources.getString(R.string.question_layer)+" "+questions[mRandomGenerator]+"?"
        //Toast.makeText(context,mRandomGenerator.toString() ,Toast.LENGTH_LONG).show()


        return root
    }

    fun checkSolution(view: View){
        if(rbApplication.isChecked){
            nAnswer=1


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}