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
import kotlin.math.pow

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
            textView.text = intToIpString(generateRandomMask())
            //title.text = titleString()
            checkButton.setOnClickListener {
                val alertDialogBuilder = AlertDialog.Builder(it.context)
                if (isCorrect(answer.text.toString())){
                    //Log.i("Respuesta", "Correcta")

                    //textView.text = generateRandomNumber()
                    textView.text = intToIpString(generateRandomMask())
                    answer.text = ""
                    solutionTextView.text = ""
                    // Correct answer, changing ImageView
                    setImage(true)
                    if (resultImage != null) {
                        // Visible
                        putImage()
                        Timer("SettingUp", false).schedule(2000) { // Waiting
                            removeImage() // ImageView disappears after 2s
                        }
                    }

                } else {
                    // Wrong answer
                    setImage(false)
                    if (resultImage != null) {

                        putImage()
                        Timer("SettingUp", false).schedule(2000) {
                            removeImage()
                        }
                        // Deleting wrong answer
                        answer.text=""
                    }

                }
            }
            solutionButton.setOnClickListener {
                solutionTextView.text = resources.getString(R.string.solution)
                //answer.text = "1"
                answer.text=obtainSolution()
            }

        }
        if (savedInstanceState != null) {
            mMask = savedInstanceState.getInt(STATE_MASK)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun intToIpString(ipAddress: Int): String? {
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
        return (-mMask-2).toInt().toString()
        /*return if (mDirectConversion) {
            mNumberToConvert.toString().uppercase()
        } else {

            Integer.toBinaryString(mNumberToConvert).uppercase()
        }*/

    }

    fun generateRandomMask(): Int {
        var maxOffset = 22

        // Add 2 because 0 is not a valid mask
        val offset: Int = mRandom.nextInt(maxOffset) + 2
        Log.d("Offset", offset.toString())
        //mMask = 0x7fffffff shl offset
        val maximum : Long = 2.0.pow(32).toLong()-1
        val offset_pow : Int = 2.0.pow(offset).toInt()-1
        mMask = (maximum.toInt() - offset_pow).toInt()
        Log.d("MaskFCR", mMask.toString())
        return mMask
    }

    fun isCorrect(answer: String): Boolean {
        //return true
        return obtainSolution() == answer.uppercase()
    }

    /*companion object {
        private const val STATE_NUMBER_TO_CONVERT = "mNumberToConvert"
    }*/


    fun setNumbertoConvert(param: Int){
        //mNumberToConvert= param;  // FIXME
    }

}