package es.imovil.fcrtrainer.ui.codes.LogicOperations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.databinding.FragmentLogicOperationsBinding
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

        num1=loViewModel.num1
        num2=loViewModel.num2
        operation=loViewModel.operation
        solution=loViewModel.solution

        tv_n1=binding.tvN1
        tv_n2=binding.tvN2
        b_check=binding.bCheck
        b_solution=binding.bSolution
        ed_solution=binding.input
        tv_operation=binding.tvOperation

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
            Toast.makeText(context,"No es correcto",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Correcto!!",Toast.LENGTH_SHORT).show()
            createQuestion()
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