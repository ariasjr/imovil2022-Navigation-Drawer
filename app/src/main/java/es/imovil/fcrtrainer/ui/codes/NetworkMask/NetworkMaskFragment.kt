package es.imovil.fcrtrainer.ui.codes.NetworkMask

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.NetworkMaskFragmentBinding
import es.imovil.fcrtrainer.ui.highscores.HighscoreManager
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.pow

class NetworkMaskFragment : Fragment(){

    companion object {
        fun newInstance() = NetworkMaskFragment()
    }

    protected var mRandom = Random()
    private val STATE_HOST = "host"
    protected var mHost = 0

    private var _binding: NetworkMaskFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NetworkMaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  NetworkMaskFragmentBinding.inflate(inflater, container, false)

        val galleryViewModel =
            ViewModelProvider(this)[NetworkMaskViewModel::class.java]

        val textView: TextView = binding.binTarget
        val solutionTextView: TextView = binding.solutionText
        val checkButton: Button = binding.checkbutton
        val solutionButton: Button = binding.solution
        val answer: TextView = binding.binTextViewAnswer
        val resultImage: ImageView? = binding.resultImageView
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text =  generateRandomHostNum(2..254).toString();
            checkButton.setOnClickListener {
                val alertDialogBuilder = AlertDialog.Builder(it.context)
                if (isCorrect(answer.text.toString())){
                    //Log.i("Respuesta", "Correcta")

                    //textView.text = generateRandomNumber()
                    textView.text = generateRandomHostNum(2..254).toString();
                    answer.text = ""
                    solutionTextView.text = ""
                    // Correct answer, changing ImageView
                    setImage(true)
                    if (resultImage != null) {
                        // Visible
                        putImage()
                        Timer("SettingUp", false).schedule(2000) { // Waiting
                            removeImage() // ImageView disappears after 2s
                        }
                    }

                } else {
                    // Wrong answer
                    setImage(false)
                    if (resultImage != null) {

                        putImage()
                        Timer("SettingUp", false).schedule(2000) {
                            removeImage()
                        }
                        // Deleting wrong answer
                        answer.text=""
                    }

                }
            }
            solutionButton.setOnClickListener {
                solutionTextView.text = resources.getString(R.string.solution)
                //answer.text = "1"
                answer.text=obtainSolution()
            }

        }
        if (savedInstanceState != null) {
            mHost = savedInstanceState.getInt(STATE_HOST)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_HOST, mHost)
    }

    fun setImage(result:Boolean){
        if(result) {
            binding.resultImageView?.setImageResource(R.drawable.ic_correct)
            HighscoreManager.addPoint(requireContext(), R.string.menu_network_mask)
        }
        else{
            binding.resultImageView?.setImageResource(R.drawable.ic_incorrect)
            HighscoreManager.remPoint(requireContext(), R.string.menu_network_mask)
        }
    }

    fun putImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.visibility=View.VISIBLE
        }
    }

    fun removeImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_correct)
            resultImage.visibility=View.INVISIBLE
        }

    }

    //Función para obtener solución correcta
    fun obtainSolution(): String {

        var aux : Int = binding.binTarget.text.toString().toInt();

        if (aux == 2){
            return "255.255.255.252";
        }
        if ((aux >=3) &&  (aux <= 6)){
            return "255.255.255.248";
        }
        if ((aux >=7) &&  (aux <= 14)){
            return "255.255.255.240";
        }
        if ((aux >=15) &&  (aux <= 30)){
            return "255.255.255.224";
        }
        if ((aux >=31) &&  (aux <= 62)){
            return "255.255.255.192";
        }
        if ((aux >=63) &&  (aux <=126)){
            return "255.255.255.128";
        }
        if ((aux >=127) &&  (aux <= 254)){
            return "255.255.255.0";
        }
        return "";
    }

    //Genera un número aleatorio de hosts (entre 2 y 254 para hacerlo más simple)
    fun generateRandomHostNum(valores: IntRange): Int {
        var r = Random()
        var valorRandom = r.nextInt(valores.last - valores.first) + valores.first
        return valorRandom
    }

    fun isCorrect(answer: String): Boolean {
        //return true
        return obtainSolution() == answer.uppercase()
    }
}