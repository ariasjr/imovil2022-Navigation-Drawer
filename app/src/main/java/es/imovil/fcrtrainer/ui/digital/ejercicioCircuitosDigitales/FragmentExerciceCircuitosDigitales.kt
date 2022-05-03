package es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales

import android.content.res.Resources
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import es.imovil.fcrtrainer.R
import es.imovil.fcrtrainer.databinding.FragmentExerciceCircuitosDigitalesBinding
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random
import kotlin.random.nextInt


class FragmentExerciceCircuitosDigitales : Fragment() {
    private var _binding: FragmentExerciceCircuitosDigitalesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var a:Boolean=false
    private var b:Boolean=false
    private var c:Boolean=false
    private var d:Boolean=false
    private var ids:Array<Int> = arrayOf(R.drawable.circuito1,R.drawable.circuito2,R.drawable.circuito3)
    private var soluciones= arrayOfNulls<Boolean>(3)
    val r:Random = Random(9043447234336234234)
    var circuito:Int =0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ExerciceCircuitosDigitalesViewModel::class.java)

        _binding = FragmentExerciceCircuitosDigitalesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        generarNivel()
        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.boton0.setOnClickListener {
            if(soluciones[circuito-1]==false){
                solucion(true)
                generarNivel()
            }else{
                solucion(false)
            }
        }
        binding.boton1.setOnClickListener {

            if(soluciones[circuito-1]==true){
                solucion(true)
                generarNivel()
            }else {
                solucion(false)
            }
        }

        return root
    }

    fun generarNivel(){
        generarValores()
        generaCircuitos()
        circuito = generaCircuitos()
        soluciones= arrayOf(circuito1(),circuito2(),circuito3())
        binding.circuitos.setImageDrawable(resources.getDrawable(ids.get(circuito-1)))
        resources.getDrawable(ids.get(circuito),)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun generarValores(){
        a=r.nextBoolean()
        b=r.nextBoolean()
        c=r.nextBoolean()
        d=r.nextBoolean()
        binding.textA.text="A:"+ booleanToInt(a)
        binding.textB.text="B:"+ booleanToInt(b)
        binding.textC.text="C:"+ booleanToInt(c)
        binding.textD.text="D:"+ booleanToInt(d)
    }
    fun generaCircuitos():Int {
        return r.nextInt(1..ids.size-1)
    }

    fun circuito1():Boolean{
        return (a != b) || !(b && c)
    }
    fun circuito2():Boolean{
        return (a || b) != (b && c)
    }
    fun circuito3():Boolean{
        return (a != b) && !(c || d)
    }
    fun booleanToInt(b: Boolean): Int {
        return if (b) 1 else 0
    }

    fun solucion(s:Boolean){
        if(s){
            binding.solucion.setImageResource(R.drawable.ic_correct)
        }else{
            binding.solucion.setImageResource(R.drawable.ic_incorrect)
        }
        val resultado:ImageView = binding.solucion
        resultado.visibility=View.VISIBLE
        Timer("SettingUp", false).schedule(500) {
            resultado.visibility=View.INVISIBLE//Esperamos removeImage() //El image view desaparece a los 2 seg
        }

    }

}