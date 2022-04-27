package es.imovil.fcrtrainer.ui.codes.signed_magnitude

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import es.imovil.fcrtrainer.R
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

    lateinit var mTitleTextView: TextSwitcher

    lateinit var mResult:View
    lateinit var mResultImage:ImageView

    private var mAntovershoot = AnticipateOvershootInterpolator()

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

        mResult = binding.overlapped.result
        mResultImage = binding.overlapped.resultimage

        mTitleTextView = binding.exercisetitle
        mTitleTextView.setInAnimation(context, R.anim.slide_in_right)
        mTitleTextView.setOutAnimation(context, R.anim.slide_out_left)

        mCorrectAnswer = smViewModel.solution
        mNumberToConverString = smViewModel.number

        mAnswerEditText = binding.textViewAnswer
        val mCheckButton = binding.checkbutton
        val mChangeDirectionButton = binding.change
        val mSolutionButton = binding.seesolution
        mNumberToConvertTextSwitcher = binding.tvNumber

        mRandomGenerator = Random()

        mCheckButton.setOnClickListener {
                checkSolution(mAnswerEditText.text.toString())
        }

        mChangeDirectionButton.setOnClickListener {
                mBinaryToDecimal = mBinaryToDecimal xor true
                setTitle()
                newQuestion()
        }

        mSolutionButton.setOnClickListener {
                showSolution()
        }


        if(isSavedContext()) restoreQuestion()
        else newQuestion()

        setTitle()

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
            showAnimationAnswer(false)
        } else {
            showAnimationAnswer(true)
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

    private fun setTitle() {
        mTitleTextView.setText(titleString())
    }

    protected fun titleString(): String? {
        val formatStringId: Int
        formatStringId = if (mBinaryToDecimal) {
            R.string.convert_dec_to_sign_and_magnitude
        } else {
            R.string.convert_sign_and_magnitude_to_dec
        }
        val formatString = resources.getString(formatStringId)
        return String.format(formatString, numberOfBits())
    }

    protected fun showAnimationAnswer(correct: Boolean) {
        // Fade in - fade out
        mResult.visibility = View.VISIBLE
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = 300
        animation.fillBefore = true
        animation.fillAfter = true
        animation.repeatCount = Animation.RESTART
        animation.repeatMode = Animation.REVERSE
        mResult.startAnimation(animation)
        var drawableId: Int = R.drawable.correct
        if (!correct) {
            drawableId = R.drawable.incorrect
        }
        mResultImage.setImageDrawable(ContextCompat.getDrawable(this.requireContext(), drawableId))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mResultImage.animate().setDuration(300).setInterpolator(mAntovershoot)
                .scaleX(1.5f).scaleY(1.5f)
                .withEndAction(Runnable { // Back to its original size after the animation's end
                    mResultImage.animate().scaleX(1f).scaleY(1f)
                })
        }
    }



}