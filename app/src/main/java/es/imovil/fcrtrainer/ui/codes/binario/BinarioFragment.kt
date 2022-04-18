package es.imovil.fcrtrainer.ui.codes.binario

import android.app.AlertDialog
import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentBinarioBinding
import kotlin.math.pow
import kotlin.random.Random


class BinarioFragment : Fragment() {
    private var mNumberToConvert = 0
    private var mDirectConversion = false
    private val mRandomGenerator = Random(9999999999)

    private var _binding: FragmentBinarioBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[BinarioViewModel::class.java]

        _binding = FragmentBinarioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.binTarget
        val title: TextView = binding.binTitle
        val solutionTextView: TextView = binding.solutionText
        val swapButton: Button = binding.change
        val checkButton: Button = binding.checkbutton
        val solutionButton: Button = binding.solution
        val answer: TextView = binding.binTextViewAnswer

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
                val alertDialogBuilder = AlertDialog.Builder(it.context)
                if (isCorrect(answer.text.toString())){
                    Log.i("Respuesta", "Correcta")
                    alertDialogBuilder.setMessage(resources.getString(R.string.hex_correct)).show()
                    textView.text = generateRandomNumber()
                    answer.text = ""
                    solutionTextView.text = ""
                } else {
                    alertDialogBuilder.setMessage(resources.getString(R.string.hex_wrong)).show()


                }
            }
            solutionButton.setOnClickListener {
                solutionTextView.text = resources.getString(R.string.solution)
                answer.text=obtainSolution().toString()
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

    fun titleString(): String {
        val formatStringId: Int = if (mDirectConversion) {
            R.string.convert_bin_to_dec
        } else {
            R.string.convert_dec_to_bin
        }
        return resources.getString(formatStringId)
    }

    fun numberOfBits(): Double {
        return 6.0
    }

    fun obtainSolution(): String {
        return if (mDirectConversion) {
            mNumberToConvert.toString().uppercase()
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
            mNumberToConvert.toString().uppercase()
        }
    }

    fun isCorrect(answer: String): Boolean {
        return obtainSolution() == answer.uppercase()
    }

    companion object {
        private const val STATE_NUMBER_TO_CONVERT = "mNumberToConvert"
    }



}