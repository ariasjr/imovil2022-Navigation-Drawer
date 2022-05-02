package es.imovil.fcrtrainer.ui.digital.logicgate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentLogicgateBinding
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random as Random

class LogicgateFragment : Fragment() {

    private var _binding: FragmentLogicgateBinding? = null
    private val binding get() = _binding!!

    private val images by lazy{
        resources.obtainTypedArray(R.array.logic_gate)
    }
    private var correctValue = 0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[LogicgateViewModel::class.java]

        _binding = FragmentLogicgateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val title: TextView = binding.logicgateTitle
        val titleSolution: TextView = binding.solutionText
        val andButton: Button = binding.and
        val nandButton: Button = binding.nand
        val orButton: Button = binding.or
        val norButton: Button = binding.nor
        val notButton: Button = binding.not
        val xorButton: Button = binding.xor
        val bufferButton: Button = binding.triestado
        val solutionButton: Button = binding.solucion
        val image: ImageView = binding.imageLogicgate
        val resultImage: ImageView = binding.imageSolution

        galleryViewModel.text.observe(viewLifecycleOwner) {
            title.text = titleString()

            correctValue=Random.nextInt(0..7)
            image.setImageResource(images.getResourceId(correctValue,0))

            andButton.setOnClickListener {
                if(correctValue==0){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            nandButton.setOnClickListener {
                if(correctValue==1){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            orButton.setOnClickListener {
                if(correctValue==2){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            norButton.setOnClickListener {
                if(correctValue==3){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            notButton.setOnClickListener {
                if(correctValue==4){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            xorButton.setOnClickListener {
                if(correctValue==5){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            bufferButton.setOnClickListener {
                if(correctValue==6){
                    setImage(true)
                    if (resultImage != null) {
                        //Ponemos en visible el image view
                        putImage()
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //El image view desaparece a los 2 seg
                        }
                    }
                    correctValue=Random.nextInt(0..7)
                    changeImage(correctValue)
                }
                else {
                    setImage(false)
                    if (resultImage != null) {
                        putImage() // Ponemos e
                        Timer("SettingUp", false).schedule(500) { //Esperamos
                            removeImage() //Imageview desaparece a los 2 seg
                        }
                    }
                }
            }
            solutionButton.setOnClickListener {
                if(correctValue == 0){
                    titleSolution.text = "La puerta correcta es la: AND"
                }
                else if (correctValue == 1){
                    titleSolution.text = "La puerta correcta es la: NAND"
                }
                else if (correctValue == 2){
                    titleSolution.text = "La puerta correcta es la: OR"
                }
                else if (correctValue == 3){
                    titleSolution.text = "La puerta correcta es la: NOR"
                }
                else if (correctValue == 4){
                    titleSolution.text = "La puerta correcta es la: NOT"
                }
                else if (correctValue == 5){
                    titleSolution.text = "La puerta correcta es la: XOR"
                }
                else if (correctValue == 6){
                    titleSolution.text = "La puerta correcta es la: TRIESTADO"
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    fun titleString(): String {
        return "Puertas lógicas"
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

    fun changeImage(x: Int){
        binding.imageLogicgate.setImageResource(images.getResourceId(x,0))
        binding.solutionText.text = ""
    }

    fun setImage(result:Boolean){
        if(result) {
            binding.imageSolution.setImageResource(R.drawable.ic_correct)
        }
        else{
            binding.imageSolution.setImageResource(R.drawable.ic_incorrect)
        }
    }

    fun putImage(){
        val resultImage: ImageView? = binding.imageSolution
        if (resultImage != null) {
            resultImage.visibility=View.VISIBLE
        }
    }

    fun removeImage(){
        val resultImage: ImageView? = binding.imageSolution
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_correct)
            resultImage.visibility=View.INVISIBLE
        }
    }


}