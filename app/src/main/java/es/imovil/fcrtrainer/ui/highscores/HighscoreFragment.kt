package es.imovil.fcrtrainer.ui.highscores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.collection.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentHighscoreBinding

class HighscoreFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentHighscoreBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var vm:HighscoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm = ViewModelProvider(this).get(HighscoreViewModel::class.java)

        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        val view = binding.root

        vm.exercises.observe(viewLifecycleOwner) {
            binding.spinnerExercise.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
        }

        vm.scores.observe(viewLifecycleOwner) {
            binding.listViewHighscores.adapter = HighscoreAdapter(requireContext(),it)
        }

        return view
    }

    private fun getCurrentExercise(): String {
        return binding.spinnerExercise.selectedItem.toString()
    }

    private fun updateScoreList() {
        vm.updateScores( HighscoreManager.getScores(requireContext(), getCurrentExercise()) )
    }

    override fun onStart() {
        super.onStart()
        val nodes = findNavController().graph.nodes

        nodes.forEach { key, value ->
            run {
                if (value.id != R.id.nav_highscore)
                    vm.addExerciseToList(value.label.toString())
            }
        }
        binding.spinnerExercise.onItemSelectedListener = this

    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.clearList()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        updateScoreList()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}