package es.imovil.fcrtrainer.ui.digital.logicgate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentLogicgateBinding
import kotlin.math.pow
import kotlin.random.Random

class LogicgateFragment : Fragment() {
    private var mNumberToConvert = 0
    private var mDirectConversion = true
    private val mRandomGenerator = Random(9999999999)

    private var _binding: FragmentLogicgateBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[LogicgateViewModel::class.java]

        _binding = FragmentLogicgateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val title: TextView = binding.hexTitle
        val solutionTextView: TextView = binding.solutionText

        galleryViewModel.text.observe(viewLifecycleOwner) {


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

    fun titleString(): String {
        val formatStringId: Int = if (mDirectConversion) {
            R.string.convert_bin_to_hex
        } else {
            R.string.convert_hex_to_bin
        }
        return resources.getString(formatStringId)
    }

    fun numberOfBits(): Double {
        return 8.0
    }

    fun obtainSolution(): String {
        return if (mDirectConversion) {
            Integer.toHexString(mNumberToConvert).uppercase()
        } else {
            Integer.toBinaryString(mNumberToConvert).uppercase()
        }

    }

    fun generateRandomNumber(): String {
        val maxNumberToConvert = 2.0.pow(numberOfBits()).toInt()
        mNumberToConvert = mRandomGenerator.nextInt(maxNumberToConvert)
        return if (mDirectConversion) {
            Integer.toBinaryString(mNumberToConvert).toString()
        } else {
            Integer.toHexString(mNumberToConvert).uppercase()
        }
    }

    fun isCorrect(answer: String): Boolean {
        return obtainSolution() == answer.uppercase()
    }

    companion object {
        private const val STATE_NUMBER_TO_CONVERT = "mNumberToConvert"
    }


}