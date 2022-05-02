package es.imovil.fcrtrainer.ui.codes.num_hosts

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.NumHostsFragmentBinding
import es.imovil.fcrtrainer.ui.codes.binario.BinarioFragment
import java.util.*
import kotlin.concurrent.schedule

class NumHostsFragment : Fragment() {

    companion object {
        fun newInstance() = NumHostsFragment()
    }

    protected var mRandom = Random()
    private val STATE_MASK = "mMask"
    protected var mMask = 0

    private var _binding: NumHostsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NumHostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NumHostsFragmentBinding.inflate(inflater, container, false)

        val galleryViewModel =
            ViewModelProvider(this)[NumHostsViewModel::class.java]

        val textView: TextView = binding.binTarget
        //val title: TextView = binding.binTitle
        val solutionTextView: TextView = binding.solutionText
        val checkButton: Button = binding.checkbutton
        val solutionButton: Button = binding.solution
        val answer: TextView = binding.binTextViewAnswer
        val resultImage: ImageView? = binding.resultImageView
        galleryViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = generateRandomNumber()
            //title.text = titleString()
            checkButton.setOnClickListener {
                val alertDialogBuilder = AlertDialog.Builder(it.context)
                if (isCorrect(answer.text.toString())){
                    Log.i("Respuesta", "Correcta")

                    textView.text = generateRandomNumber()
                    answer.text = ""
                    solutionTextView.text = ""
                    //Resultado correcto, cambiamos el image view
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(2000) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }

                } else {
                    //Resultado incorrecto, image view lo refleja
                    setImage(false)
                    if (resultImage != null) {

                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(2000) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                        //Borramos el numero erroneo introducido:
                        answer.text=""
                    }

                }
            }
            solutionButton.setOnClickListener {
                solutionTextView.text = resources.getString(R.string.solution)
                answer.text=obtainSolution().toString()
            }

        }
        if (savedInstanceState != null) {
            mNumberToConvert = savedInstanceState.getInt(BinarioFragment.STATE_NUMBER_TO_CONVERT)
        }


        return binding.root
        //return inflater.inflate(R.layout.num_hosts_fragment, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun intToIpString(ipAddress: Int): String? {
        val bytes = intArrayOf(
            ipAddress shr 24 and 0xff,
            ipAddress shr 16 and 0xff,
            ipAddress shr 8 and 0xff,
            ipAddress and 0xff
        )
        return (bytes[0].toString()
                + "." + bytes[1].toString()
                + "." + bytes[2].toString()
                + "." + bytes[3].toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_MASK, mMask)
    }

    fun setImage(result:Boolean){
        if(result) {
            binding.resultImageView?.setImageResource(R.drawable.ic_correct)
        }
        else{
            binding.resultImageView?.setImageResource(R.drawable.ic_incorrect)
        }
    }

    fun putImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.visibility=View.VISIBLE
        }

    }


    fun removeImage(){
        val resultImage: ImageView? = binding.resultImageView
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_correct)
            resultImage.visibility=View.INVISIBLE
        }

    }

    fun obtainSolution(): String {
        return if (mDirectConversion) {
            mNumberToConvert.toString().uppercase()
        } else {

            Integer.toBinaryString(mNumberToConvert).uppercase()
        }

    }

    protected fun generateRandomMask(): Int {
        //val level: Level = PreferenceUtils.getLevel(activity)
        var maxOffset = 8
        /*when (level) {
            BEGINNER -> maxOffset = 4
            INTERMEDIATE -> maxOffset = 16
            PROFICIENCY -> maxOffset = 26
        }*/

        // Add 1 because 0 is not a valid mask
        val offset: Int = mRandom.nextInt(maxOffset) + 1
        return -0x1 shl offset
    }

    fun isCorrect(answer: String): Boolean {
        return obtainSolution() == answer.uppercase()
    }

    /*companion object {
        private const val STATE_NUMBER_TO_CONVERT = "mNumberToConvert"
    }*/


    fun setNumbertoConvert(param: Int){
        mNumberToConvert= param;
    }


    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NumHostsViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}