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
    private lateinit var arrayPossibleAnswers: Array<String>

    private lateinit var bCheck: Button
    private lateinit var bSolution: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbApplication: RadioButton
    private lateinit var rbTransport: RadioButton
    private lateinit var rbInternet: RadioButton
    private lateinit var rbLink: RadioButton
    private lateinit var resultImage: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvQuestion:TextView
    private var protocol: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val galleryViewModel=ViewModelProvider(this)[NetworkLayerViewModel::class.java]

        _binding= FragmentNetworkLayerBinding.inflate(inflater, container, false)
        val root: View =binding.root

        bCheck=binding.bCheck; bSolution=binding.bSolution
        radioGroup=binding.radioGroup
        rbApplication=binding.rbApplication; rbTransport=binding.rbTransport; rbInternet=binding.rbInternet; rbLink=binding.rbLink
        tvQuestion=binding.tvQuestion; tvTitle=binding.tvTitle
        resultImage=binding.resultImageView
        questions= resources.getStringArray(R.array.network_layer_questions); answers=resources.getStringArray(R.array.network_layer_answers)

        galleryViewModel.text.observe(viewLifecycleOwner){
            tvTitle.text=titleString()
            bCheck.isEnabled=false
            val mRandomGenerator= calculateNumber()
            tvQuestion.text=resources.getString(R.string.question_layer)+" "+questions[mRandomGenerator]+"?"
            protocol=questions[mRandomGenerator]

            radioGroup.setOnCheckedChangeListener(this)

            bCheck.setOnClickListener {
                if(arrayPossibleAnswers.contains(protocol)){
                    correctAnswer()
                    protocol=questions[calculateNumber()]
                    tvQuestion.text=resources.getString(R.string.question_layer)+" "+protocol+"?"
                    radioGroup.clearCheck()
                }
                else incorrectAnswer()
            }

            bSolution.setOnClickListener {
                if (resources.getStringArray(R.array.protocols_application).contains(protocol))
                    radioGroup.check(rbApplication.id)
                if (resources.getStringArray(R.array.protocols_transport).contains(protocol))
                    radioGroup.check(rbTransport.id)
                if (resources.getStringArray(R.array.protocols_internet).contains(protocol))
                    radioGroup.check(rbInternet.id)
                if (resources.getStringArray(R.array.protocols_link).contains(protocol))
                    radioGroup.check(rbLink.id)
            }
        }

        if(savedInstanceState!=null)
            protocol=savedInstanceState.getString(STATE_NAME_PROTOCOL).toString()

        return root
    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        bCheck.isEnabled=true

        when(idRadio){
            rbApplication.id -> arrayPossibleAnswers=resources.getStringArray(R.array.protocols_application)
            rbTransport.id -> arrayPossibleAnswers=resources.getStringArray(R.array.protocols_transport)
            rbInternet.id -> arrayPossibleAnswers=resources.getStringArray(R.array.protocols_internet)
            rbLink.id -> arrayPossibleAnswers=resources.getStringArray(R.array.protocols_link)
        }
    }

    private fun correctAnswer(){
        setImage(true)
        putImage()
        Timer("SettingUp", false).schedule(2000) { //Esperamos
            removeImage() //El image view desaparece a los 2 seg
        }
    }

    private fun incorrectAnswer(){
        setImage(false)
        putImage() // Ponemos e
        Timer("SettingUp", false).schedule(2000) { //Esperamos
            removeImage() //Imageview desaparece a los 2 seg
        }
    }

    fun calculateNumber():Int{
        return Random.nextInt(10)
    }

    fun titleString():String{
        return resources.getString(R.string.layer_title)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun setImage(result:Boolean){
        if(result) {
            binding.resultImageView?.setImageResource(R.drawable.ic_correct)
        }
        else{
            binding.resultImageView?.setImageResource(R.drawable.ic_incorrect)
        }
    }

    private fun putImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.visibility=View.VISIBLE
        }
    }

    private fun removeImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_correct)
            resultImage.visibility=View.INVISIBLE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_NAME_PROTOCOL, protocol)
    }

    companion object{
        private const val STATE_NAME_PROTOCOL="name_protocol"
    }

}