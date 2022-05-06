package es.imovil.fcrtrainer.ui.codes.twosComplement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentTwosComplementBinding
import es.imovil.fcrtrainer.ui.codes.hexadecimal.HexadecimalFragment
import es.imovil.fcrtrainer.ui.highscores.HighscoreManager
import java.util.*
import kotlin.math.pow
import kotlin.properties.Delegates


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwosComplementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwosComplementFragment : Fragment() {

    private var mNumberToConvert = 0
    lateinit var mNumberToConvertTextSwitcher: TextView
    lateinit var mAnswerEditText: EditText
    lateinit var mRandomGenerator: Random
    private var mBinaryToDecimal = true
    private var _binding: FragmentTwosComplementBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTwosComplementBinding.inflate(inflater, container, false)
        binding.tcTitle.text = titleString()

        mAnswerEditText = binding.tcAnswer
        val mCheckButton =  binding.tcComprobar
        val mChangeDirectionButton = binding.tcInverso
        val mSolutionButton = binding.tcSolucion
        mNumberToConvertTextSwitcher = binding.tcNumber

        mRandomGenerator = Random()

        mCheckButton.setOnClickListener {
            checkSolution(mAnswerEditText.text.toString())
        }

        mChangeDirectionButton.setOnClickListener {
            mBinaryToDecimal = mBinaryToDecimal xor true
            binding.tcTitle.text = titleString()
            newQuestion()
        }

        mSolutionButton.setOnClickListener {
            showSolution()
        }

        newQuestion()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newQuestion() {
        clearAnswer()

        mNumberToConvertTextSwitcher.text = generateRandomNumber()
    }

    private fun checkSolution(answer: String) {
        if (answer == "" || !isCorrect(answer)) {
            Toast.makeText(context, "Respuesta INCORRECTA", Toast.LENGTH_SHORT).show()
            HighscoreManager.remPoint(requireContext(), R.string.twos_complement)
        } else {
            // Correct answer
            Toast.makeText(context, "Respuesta CORRECTA", Toast.LENGTH_SHORT).show()
            HighscoreManager.addPoint(requireContext(), R.string.twos_complement)
            newQuestion()
        }
    }

    private fun showSolution() {
        (mAnswerEditText as TextView).text = obtainSolution()
    }

    private fun clearAnswer() {
        (mAnswerEditText as TextView).text = ""
    }

    fun generateRandomNumber(): String {
        val min = -(2.0.pow(numberOfBits() - 1)).toInt()
        val max = (2.0.pow(numberOfBits() - 1) - 1).toInt()
        mNumberToConvert = mRandomGenerator.nextInt(max - min + 1) + min
        return if (mBinaryToDecimal){
            twosComplement(mNumberToConvert)
        }
        else{
            mNumberToConvert.toString().uppercase()
        }
    }

    fun twosComplement(n: Int): String{
            if (n >= 0) {
                var nPositivo = Integer.toBinaryString(n).toString()
                var tam = nPositivo.length
                for(i in 1..numberOfBits()-tam){
                    nPositivo = "0$nPositivo"
                }
                return nPositivo
            } else {
                var nPositivo = Integer.toBinaryString(n*-1).toString()
                var tam = nPositivo.length
                var flag = true
                var sb: StringBuilder
                for (i in nPositivo.length-1 downTo 0) {
                    if (nPositivo[i].toString().compareTo(1.toString()) == 0 && flag) {
                        flag = false
                    }
                    else if (!flag) {
                        if(nPositivo[i].toString().compareTo(0.toString()) == 0) {
                            sb = StringBuilder(nPositivo).also { it.setCharAt(i, '1') }
                            nPositivo = sb.toString()
                        }
                        else {
                            sb = StringBuilder(nPositivo).also { it.setCharAt(i, '0') }
                            nPositivo = sb.toString()
                        }
                    }
                }
                for(i in 1..numberOfBits()-tam){
                    nPositivo = "1$nPositivo"
                }
                return nPositivo
            }
    }

    fun obtainSolution(): String {
        return if(mBinaryToDecimal){
            mNumberToConvert.toString().uppercase()
        } else{
            twosComplement(mNumberToConvert)
        }
    }

    fun titleString(): String {
        val formatStringId: Int = if (mBinaryToDecimal) {
            R.string.convert_tc_to_dec
        } else {
            R.string.convert_dec_to_tc
        }
        return resources.getString(formatStringId)
    }

    private fun numberOfBits(): Int {
        return 4
    }

    private fun isCorrect(answer:String):Boolean{
        return answer == obtainSolution()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_NUMBER_TO_CONVERT, mNumberToConvert)
    }

    companion object {
        private const val STATE_NUMBER_TO_CONVERT = "mNumberToConvert"
    }

}