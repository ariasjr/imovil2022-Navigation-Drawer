package es.imovil.fcrtrainer.ui.codes.LogicOperations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.databinding.FragmentLogicOperationsBinding


class LogicOperationsFragment : Fragment(){
    private var _binding: FragmentLogicOperationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loViewModel =
            ViewModelProvider(this).get(LogicOperationsViewModel::class.java)

        _binding = FragmentLogicOperationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLo
        loViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}