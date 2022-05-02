package es.imovil.fcrtrainer.ui.networks.networklayer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentNetworkLayerBinding
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

class NetworkLayerFragment : Fragment(), RadioGroup.OnCheckedChangeListener {

    private var _binding: FragmentNetworkLayerBinding?=null
    private val binding get()=_binding!!

    private lateinit var questions: Array<String>
    private lateinit var answers: Array<String>
    private lateinit var arrayPosiblesRespuestas: Array<String>

    private lateinit var bCheck: Button
    private lateinit var bSolution: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbApplication: RadioButton
    private lateinit var rbTransport: RadioButton
    private lateinit var rbInternet: RadioButton
    private lateinit var rbLink: RadioButton
    private lateinit var resultImage: ImageView
    private lateinit var tvQuestion:TextView
    private var protocol: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentNetworkLayerBinding.inflate(inflater, container, false)
        val root: View =binding.root

        bCheck=binding.bCheck
        bSolution=binding.bSolution
        radioGroup=binding.radioGroup
        rbApplication=binding.rbApplication
        rbTransport=binding.rbTransport
        rbInternet=binding.rbInternet
        rbLink=binding.rbLink
        tvQuestion=binding.tvQuestion
        resultImage=binding.resultImageView
        questions= resources.getStringArray(R.array.network_layer_questions)
        answers=resources.getStringArray(R.array.network_layer_answers)

        val mRandomGenerator= Random.nextInt(10)
        tvQuestion.text=resources.getString(R.string.question_layer)+" "+questions[mRandomGenerator]+"?"
        protocol=questions[mRandomGenerator]
        //Toast.makeText(context, protocol, Toast.LENGTH_LONG).show()

        radioGroup.setOnCheckedChangeListener(this)

        bCheck.setOnClickListener {
            if(arrayPosiblesRespuestas.contains(protocol)){
                respuestaCorrecta()
                val x=Random.nextInt(10)
                protocol=questions[x]
                tvQuestion.text=resources.getString(R.string.question_layer)+" "+protocol+"?"
                radioGroup.clearCheck()
            }
            else respuestaIncorrecta()
        }

        return root
    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        when(idRadio){
            rbApplication?.id -> arrayPosiblesRespuestas=resources.getStringArray(R.array.protocols_application)
            rbTransport?.id -> arrayPosiblesRespuestas=resources.getStringArray(R.array.protocols_transport)
            rbInternet?.id -> arrayPosiblesRespuestas=resources.getStringArray(R.array.protocols_internet)
            rbLink?.id -> arrayPosiblesRespuestas=resources.getStringArray(R.array.protocols_link)
        }
    }

    fun respuestaCorrecta(){
        setImage(true)
        if (resultImage != null) { //Ponemos en visible el image view
            putImage()
            Timer("SettingUp", false).schedule(2000) { //Esperamos
                removeImage() //El image view desaparece a los 2 seg
            }
        }
    }

    fun respuestaIncorrecta(){ //Resultado incorrecto, image view lo refleja
        setImage(false)
        if (resultImage != null) {
            putImage() // Ponemos e
            Timer("SettingUp", false).schedule(2000) { //Esperamos
                removeImage() //Imageview desaparece a los 2 seg
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    fun setImage(result:Boolean){
        if(result) binding.resultImageView?.setImageResource(R.drawable.ic_correct)
        else binding.resultImageView?.setImageResource(R.drawable.ic_incorrect)
    }

    fun putImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) resultImage.visibility=View.VISIBLE
    }

    fun removeImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_correct)
            resultImage.visibility=View.INVISIBLE
        }
    }

}