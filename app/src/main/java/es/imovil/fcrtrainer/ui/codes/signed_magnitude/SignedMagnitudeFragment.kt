package es.imovil.fcrtrainer.ui.codes.signed_magnitude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.databinding.FragmentSignedMagnitudeBinding

class SignedMagnitudeFragment : Fragment() {

    private var _binding: FragmentSignedMagnitudeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val smViewModel =
            ViewModelProvider(this).get(SignedMagnitudeViewModel::class.java)

        _binding = FragmentSignedMagnitudeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvSignedMagnitude
        smViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}