package es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentExerciceCircuitosDigitalesBinding
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


class FragmentExerciceCircuitosDigitales : Fragment() {
    private var _binding: FragmentExerciceCircuitosDigitalesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var a:Boolean=false
    private var b:Boolean=false
    private var c:Boolean=false
    val r = Random(73426472834)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ExerciceCircuitosDigitalesViewModel::class.java)

        _binding = FragmentExerciceCircuitosDigitalesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        generarValores()


        when(generaCircuitos()){
            0->{
                var resources: Resources = requireContext().resources
                var resourceId = resources.getIdentifier("circuito1","drawable",requireContext().packageName)
                binding.circuitos.setImageDrawable(resources.getDrawable(R.drawable.circuito1))
            }
            1->{
                var resources: Resources = requireContext().resources
                var resourceId = resources.getIdentifier("circuito2","drawable",requireContext().packageName)
                binding.circuitos.setImageDrawable(resources.getDrawable(R.drawable.circuito2))
            }
            else->{
                var resources: Resources = requireContext().resources
                var resourceId = resources.getIdentifier("circuito3","drawable",requireContext().packageName)
                binding.circuitos.setImageDrawable(resources.getDrawable(R.drawable.circuito3))
            }
        }


        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun generarValores(){
        a=r.nextBoolean()
        b=r.nextBoolean()
        c=r.nextBoolean()
    }
    fun generaCircuitos():Int {
        when (Random(443).nextInt(0..2)) {
            0 -> return 0
            1 -> return 1
            else -> return 2
        }
    }

    fun circuito1():Boolean{
        return (a != b) || !(b && c)
    }
    fun circuito2():Boolean{
        return (a || b) != (b && c)
    }
    fun circuito3():Boolean{
        return (a != b) && !(b || c)
    }
}