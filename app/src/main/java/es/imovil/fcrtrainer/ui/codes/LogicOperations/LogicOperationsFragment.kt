package es.imovil.fcrtrainer.ui.codes.LogicOperations

import es.imovil.fcrtrainer.R
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import es.imovil.fcrtrainer.databinding.FragmentLogicOperationsBinding
import es.imovil.fcrtrainer.ui.highscores.HighscoreManager
import java.util.*
import kotlin.math.pow


class LogicOperationsFragment : Fragment(){

    val loViewModel:LogicOperationsViewModel by activityViewModels()
    private lateinit var num1:String
    private lateinit var num2:String
    private lateinit var solution:String
    private lateinit var operation:String

    private lateinit var tv_n1:TextView
    private lateinit var tv_n2:TextView
    private lateinit var tv_operation:TextView
    private lateinit var ed_solution:EditText
    private lateinit var b_check:Button
    private lateinit var b_solution:Button

    lateinit var mResult:View
    lateinit var mResultImage:ImageView
    private var mAntovershoot=AnticipateOvershootInterpolator()


    lateinit var mRandomGenerator:Random


    private var _binding: FragmentLogicOperationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentLogicOperationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mResult=binding.overlap.result
        mResultImage=binding.overlap.resultimage

        num1=loViewModel.num1
        num2=loViewModel.num2
        operation=loViewModel.operation
        solution=loViewModel.solution

        tv_n1=binding.LOentrada1
        tv_n2=binding.LOentrada2
        b_check=binding.LObCalcular
        b_solution=binding.LObSolucion
        ed_solution=binding.textViewAnswer
        tv_operation=binding.LOoperacion

        mRandomGenerator= Random()

        b_solution.setOnClickListener(){
            showSolution()
        }
        b_check.setOnClickListener(){
            checkSolution(ed_solution.text.toString())
        }

        if(operation!="" && num1!="" && num2!=""){
            restoreQuestion()
        }
        else {
            createQuestion()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        saveQuestion()
    }

    private fun checkSolution(sol:String){
        if(sol=="" || !isCorrect(sol)){
            showAnimationAnswer(false)
            HighscoreManager.remPoint(requireContext(), R.string.menu_hexadecimal)
        }
        else{
            showAnimationAnswer(true)
            HighscoreManager.addPoint(requireContext(), R.string.menu_logical_operations)
            createQuestion()
        }
    }

    protected fun showAnimationAnswer(correct: Boolean) {
        // Fade in - fade out
        mResult.visibility = View.VISIBLE
        val animation = AlphaAnimation(0F, 1F)
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
                .withEndAction { // Back to its original size after the animation's end
                    mResultImage.animate().scaleX(1f).scaleY(1f)
                }
        }
    }

    private fun showSolution(){
        (ed_solution as TextView).text=obtainSolution()
    }

    private fun saveQuestion(){
        loViewModel.solution=solution
        loViewModel.operation=operation
        loViewModel.num1=num1
        loViewModel.num2=num2

        loViewModel.answer=ed_solution.text.toString()
    }

    private fun createQuestion(){
        num1= randomBinary()
        tv_n1.text=num1

        num2 = randomBinary()
        tv_n2.text=num2

        operation = randomOperation()
        tv_operation.text=operation

        val entrada1 = num1.toInt(2)
        val entrada2 = num2.toInt(2)
        var result = 0
        if (operation == "AND") result = entrada1 and entrada2
        else if (operation == "OR") result = entrada1 or entrada2
        else if (operation == "XOR") result = entrada1 xor entrada2

        solution = Integer.toBinaryString(result)
        solution = completaNumeroBits(solution)

        (ed_solution as TextView).text=""
    }

    private fun restoreQuestion(){
        solution=loViewModel.solution
        operation=loViewModel.operation
        num1=loViewModel.num1
        num2=loViewModel.num2

        tv_operation.text=operation
        tv_n1.text=num1
        tv_n2.text=num2
        (ed_solution as TextView).text=loViewModel.answer
    }

    private fun obtainSolution():String{
        return solution
    }

    private fun isCorrect(sol:String):Boolean{
        return sol.equals(solution)
    }

    private fun randomBinary():String{
        val maxNumber = (2.0.pow(numberOfBits())).toInt()
        val entero: Int = mRandomGenerator.nextInt(maxNumber)
        var binario = Integer.toBinaryString(entero)

        binario = completaNumeroBits(binario)
        return binario!!
    }

    private fun numberOfBits():Int{
        return 4
    }

    private fun completaNumeroBits(binario: String): String {
        var bin = binario
        var i = bin.length
        while (i < numberOfBits()) {
            bin = "0$bin"
            i = bin.length
        }
        return bin
    }

    private fun randomOperation():String{
        val entero: Int = mRandomGenerator.nextInt(3)
        val operacion: String

        return when (entero) {
            0 -> {
                operacion = "AND"
                operacion
            }
            1 -> {
                operacion = "OR"
                operacion
            }
            else -> {
                operacion = "XOR"
                operacion
            }
        }
    }

}