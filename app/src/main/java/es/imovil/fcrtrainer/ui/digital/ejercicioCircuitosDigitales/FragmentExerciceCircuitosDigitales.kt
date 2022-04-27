package es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

import es.imovil.fcrtrainer.databinding.FragmentExerciceCircuitosDigitalesBinding




class FragmentExerciceCircuitosDigitales : Fragment() {
    private var _binding: FragmentExerciceCircuitosDigitalesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ExerciceCircuitosDigitalesViewModel::class.java)

        _binding = FragmentExerciceCircuitosDigitalesBinding.inflate(inflater, container, false)
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