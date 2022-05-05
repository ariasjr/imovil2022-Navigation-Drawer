package es.imovil.fcrtrainer.ui.codes.cidr

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
import es.imovil.fcrtrainer.databinding.FragmentCidrBinding
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random


class cidrFragment : Fragment() {

    private val mRandom = Random(9999999999)
    private var _binding: FragmentCidrBinding? = null

    private val binding get() = _binding!!
    private var mMask: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[CIDRViewModel::class.java]

        _binding = FragmentCidrBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.binTarget
        val solutionTextView: TextView = binding.solutionText
        val checkButton: Button = binding.checkbutton
        val solutionButton: Button = binding.solution
        val resultImage: ImageView? = binding.resultImageView
        val answer: TextView = binding.binTextViewAnswer
        galleryViewModel.text.observe(viewLifecycleOwner) {
            mMask = generateRandomMask()
            textView.text = intToIpString(mMask)
            checkButton.setOnClickListener {

                if (!(answer.text.toString().equals("")) && isCorrect(Integer.parseInt(answer.text.toString()), mMask)){
                    mMask = generateRandomMask()
                    textView.text = intToIpString(mMask)
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
                        putImage()
                        Timer("SettingUp", false).schedule(2000) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                        answer.text=""
                    }

                }
            }
            solutionButton.setOnClickListener {
                solutionTextView.text = resources.getString(R.string.solution)
                answer.text=obtainSolution(mMask).toString()
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_NUMBER_TO_CONVERT, mNumberToConvert)
    }
    */


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

    private fun obtainSolution(mMask: Int): Int {
        var zeroCount = 0
        var mask = mMask
        while (mask and 0x1 == 0) {
            zeroCount++
            mask = mask shr 1
        }
        return 32 - zeroCount
    }

    protected fun generateRandomMask(): Int {
        var maxOffset = 8
        val offset: Int = mRandom.nextInt(maxOffset) + 1
        return -0x1 shl offset
    }
    protected fun intToIpString(ipAddress: Int): String? {
        val bytes = intArrayOf(
            ipAddress shr 24 and 0xff,
            ipAddress shr 16 and 0xff,
            ipAddress shr 8 and 0xff,
            ipAddress and 0xff
        )
        return (Integer.toString(bytes[0])
                + "." + Integer.toString(bytes[1])
                + "." + Integer.toString(bytes[2])
                + "." + Integer.toString(bytes[3]))
    }

    fun isCorrect(answer: Int, solution : Int): Boolean {
        return obtainSolution(solution) == answer
    }




}