package es.imovil.fcrtrainer.ui.codes.binario

import android.app.AlertDialog
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
import es.imovil.fcrtrainer.databinding.FragmentBinarioBinding
import es.imovil.fcrtrainer.databinding.FragmentExercice1Binding
import kotlin.math.pow
import kotlin.random.Random


class BinarioFragment : Fragment() {
    //Con esto nos basta?
    private var mNumberToConvert = 0
    private var mDirectConversion = false
    //Numero aleatorio

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
        //TODO: COMPLETAR TODO ESTO PARA QUE TENGA SENTIDO

        return root
    }





}