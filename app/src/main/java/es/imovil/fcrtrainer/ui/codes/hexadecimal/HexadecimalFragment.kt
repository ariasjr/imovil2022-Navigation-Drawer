package es.imovil.fcrtrainer.ui.codes.hexadecimal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.databinding.FragmentExercice2Binding
import es.imovil.fcrtrainer.databinding.FragmentHexadecimalBinding
import es.imovil.fcrtrainer.ui.codes.ejercicio2.Exercice2ViewModel

class HexadecimalFragment : Fragment() {

    private var _binding: FragmentHexadecimalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(HexadecimalViewModel::class.java)

        _binding = FragmentHexadecimalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}