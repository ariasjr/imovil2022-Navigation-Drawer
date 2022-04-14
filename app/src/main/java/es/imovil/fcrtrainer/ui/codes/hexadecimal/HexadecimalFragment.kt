package es.imovil.fcrtrainer.ui.codes.hexadecimal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentHexadecimalBinding
import kotlin.math.pow
import kotlin.random.Random

class HexadecimalFragment : Fragment() {
    private var mNumberToConvert = 0
    private var mDirectConversion = false
    private val mRandomGenerator = Random(9999999999)

    private var _binding: FragmentHexadecimalBinding? = null



    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[HexadecimalViewModel::class.java]

        _binding = FragmentHexadecimalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.target
        val title: TextView = binding.hexTitle
        val solutionTextView: TextView = binding.solutionText
        val swapButton: Button = binding.change
        val checkButton: Button = binding.checkbutton
        val solutionButton: Button = binding.solution
        val answer: TextView = binding.textViewAnswer
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = generateRandomNumber()
            title.text = titleString()
            swapButton.setOnClickListener{
                mDirectConversion = !mDirectConversion
                textView.text = generateRandomNumber()
                title.text = titleString()
                answer.text = ""
                solutionTextView.text = ""
            }
            checkButton.setOnClickListener {
                if (isCorrect(answer.text.toString())){
                    Log.i("Respuesta", "Correcta")
                    textView.text = generateRandomNumber()
                    answer.text = ""
                    solutionTextView.text = ""
                } else {
                    Log.i("Respuesta", "Incorrecta")
                }
            }
            solutionButton.setOnClickListener {
                solutionTextView.text = resources.getString(R.string.solution) + ": " + obtainSolution()
            }

        }
        if (savedInstanceState != null) {
            mNumberToConvert = savedInstanceState.getInt(STATE_NUMBER_TO_CONVERT)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_NUMBER_TO_CONVERT, mNumberToConvert)
    }

    private fun titleString(): String {
        val formatStringId: Int = if (mDirectConversion) {
            R.string.convert_bin_to_hex
        } else {
            R.string.convert_hex_to_bin
        }
        return resources.getString(formatStringId)
    }

    private fun numberOfBits(): Double {
        return 8.0
    }

    private fun obtainSolution(): String {
        return if (mDirectConversion) {
            Integer.toHexString(mNumberToConvert).uppercase()
        } else {
            Integer.toBinaryString(mNumberToConvert).uppercase()
        }

    }

    private fun generateRandomNumber(): String {
        val maxNumberToConvert = 2.0.pow(numberOfBits()).toInt()
        mNumberToConvert = mRandomGenerator.nextInt(maxNumberToConvert)
        return if (mDirectConversion) {
            Integer.toBinaryString(mNumberToConvert).toString()
        } else {
            Integer.toHexString(mNumberToConvert).uppercase()
        }
    }

    private fun isCorrect(answer: String): Boolean {
        return obtainSolution() == answer.uppercase()
    }

    companion object {
        private const val STATE_NUMBER_TO_CONVERT = "mNumberToConvert"
    }


}