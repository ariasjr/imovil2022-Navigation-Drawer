package es.imovil.fcrtrainer.ui.codes.signed_magnitude

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.databinding.FragmentSignedMagnitudeBinding
import java.util.*
import kotlin.math.pow


class SignedMagnitudeFragment : Fragment() {

    //val smViewModel = ViewModelProvider(this).get(SignedMagnitudeViewModel::class.java)
    val smViewModel: SignedMagnitudeViewModel by activityViewModels()

    lateinit var mCorrectAnswer:String
    lateinit var mNumberToConverString:String

    lateinit var mNumberToConvertTextSwitcher: TextView
    lateinit var mAnswerEditText:EditText

    lateinit var mRandomGenerator:Random
    private var mBinaryToDecimal = true

    private var _binding: FragmentSignedMagnitudeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignedMagnitudeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mCorrectAnswer = smViewModel.solution
        mNumberToConverString = smViewModel.number

        mAnswerEditText = binding.etAnswer
        val mCheckButton = binding.bComprobar
        val mChangeDirectionButton = binding.bInverso
        val mSolutionButton = binding.bSolucion
        mNumberToConvertTextSwitcher = binding.tvNumber

        mRandomGenerator = Random()

        mCheckButton.setOnClickListener {
                checkSolution(mAnswerEditText.text.toString())
        }

        mChangeDirectionButton.setOnClickListener {
                mBinaryToDecimal = mBinaryToDecimal xor true
                newQuestion()
        }

        mSolutionButton.setOnClickListener {
                showSolution()
        }


        if(isSavedContext()) restoreQuestion()
        else newQuestion()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveContext()
        _binding = null
    }

    private fun saveContext(){
        smViewModel.solution = mCorrectAnswer
        smViewModel.number = mNumberToConverString
        smViewModel.answer = mAnswerEditText.text.toString()
    }

    private fun isSavedContext():Boolean{
        return (mCorrectAnswer != "")
    }

    private fun generateRandomQuestion() {
        mNumberToConverString =  generateRandomNumber()
    }

    private fun newQuestion() {
        clearAnswer()
        generateRandomQuestion()
        mNumberToConvertTextSwitcher.text = mNumberToConverString
    }

    private fun restoreQuestion(){
        mNumberToConverString = smViewModel.number
        mNumberToConvertTextSwitcher.text = mNumberToConverString

        mCorrectAnswer = smViewModel.solution
        (mAnswerEditText as TextView).text = smViewModel.answer
    }

    private fun checkSolution(answer: String) {
        if (answer == "" || !isCorrect(answer)) {
            Toast.makeText(context, "Respuesta INCORRECTA", Toast.LENGTH_SHORT).show()
        } else {
            // Correct answer
            Toast.makeText(context, "Respuesta CORRECTA", Toast.LENGTH_SHORT).show()
            newQuestion()
        }
    }

    private fun showSolution() {
        (mAnswerEditText as TextView).text = obtainSolution()
    }

    private fun clearAnswer() {
        (mAnswerEditText as TextView).text = ""
    }

    private fun generateRandomNumber():String{
        val numberOfBitsMagnitude: Int = numberOfBits() - 1
        val maxMagnitude = (2.0.pow(numberOfBitsMagnitude.toDouble()-1 )).toInt()
        val randomNumber = mRandomGenerator.nextInt(maxMagnitude)
        val magnitudeDecimal = randomNumber.toString()
        val sign: Int = mRandomGenerator.nextInt(2) // it can be 0 or 1

        val signAsString = if (sign == 0) "0" else "1"
        //val magnitudeBinary: String = BinaryConverter.binaryToStringWithNbits(
        //    randomNumber, numberOfBitsMagnitude
        //)
        val magnitudeBinary = Integer.toBinaryString(randomNumber).padStart(numberOfBitsMagnitude-1 , '0')

        if (mBinaryToDecimal) {
            if (sign == 0) {
                mCorrectAnswer = magnitudeDecimal
            } else {
                mCorrectAnswer = "-$magnitudeDecimal"
            }

            return signAsString + magnitudeBinary
        } else {
            mCorrectAnswer = signAsString + magnitudeBinary
            if (sign == 0) {
                return magnitudeDecimal
            } else {
                return "-$magnitudeDecimal"
            }

        }
    }

    private fun obtainSolution(): String {
        return mCorrectAnswer
    }

    fun isCorrect(answer:String):Boolean{
        return answer.equals(mCorrectAnswer)
    }

    private fun numberOfBits(): Int {
        return 5
    }



}