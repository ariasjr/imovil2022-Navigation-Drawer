package es.imovil.fcrtrainer.ui.codes.hexadecimal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentHexadecimalBinding
import kotlin.math.pow
import kotlin.random.Random

class HexadecimalFragment : Fragment() {
    private var mNumberToConvert = 0
    private var mDirectConversion = true
    private val mRandomGenerator = Random(1024)

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

        //val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
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
        val formatString: String = resources.getString(formatStringId)
        return java.lang.String.format(formatString, numberOfBits())
    }

    private fun numberOfBits(): Double {
        return 8.0
    }

    private fun obtainSolution(): String {
        return Integer.toHexString(mNumberToConvert).uppercase()
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
        fun newInstance(): HexadecimalFragment {
            return HexadecimalFragment()
        }
    }


}