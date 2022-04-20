package es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentExercice4Binding
import es.imovil.fcrtrainer.ui.digital.ejercicio4.ExerciceNViewModel


class ExerciceCircuitosDigitalesFragment : Fragment() {
    private var _binding: FragmentExercice4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ExerciceNViewModel::class.java)

        _binding = FragmentExercice4Binding.inflate(inflater, container, false)
        val root: View = binding.root

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
}