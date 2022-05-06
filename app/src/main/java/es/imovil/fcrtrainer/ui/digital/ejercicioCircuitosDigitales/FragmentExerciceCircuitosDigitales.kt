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
import es.imovil.fcrtrainer.ui.highscores.HighscoreManager
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random
import kotlin.random.nextInt


class FragmentExerciceCircuitosDigitales : Fragment() {
    private var _binding: FragmentExerciceCircuitosDigitalesBinding? = null

    /**
     * @param a Boolean que recibe el circuito
     * @param b Boolean que recibe el circuito
     * @param c Boolean que recibe el circuito
     * @param d Boolean que recibe el circuito
     * @param ids Array de enteros que almacena los ids de los circuitos
     * @param soluciones Array de Boolean que almacena las soluciones de todos los circuitos con los valores actuales
     * @param r Clase Random para generar valores
     * @param circuito Entero que indica la posición del circuito actual en el array ids
     */
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
        binding.textSlideshow.text=getText(R.string.enunciadoCD)
        generarNivel()
        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = getText(R.string.enunciadoCD)
        }


        /**
         * Función del botón boton0
         * Si es correcto, muestra el icono correcto y genera un nuevo nivel.
         * Si no muestra el icono incorrecto
         */
        binding.boton0.setOnClickListener {
            if(soluciones[circuito]==false){
                solucion(true)
                generarNivel()
            }else{
                solucion(false)
            }
        }
        /**
         * Función del botón boton1
         * Si es correcto, muestra el icono correcto y genera un nuevo nivel.
         * Si no muestra el icono incorrecto
         */
        binding.boton1.setOnClickListener {
            if(soluciones[circuito]==true){
                solucion(true)
                generarNivel()
            }else {
                solucion(false)
            }
        }
        return root
    }

    /**
     * Genera los Valores y el circuito correspondiente.
     * Luego se asigna a circito el circuito seleccionado y a soluciones el array de soluciones.
     * Se pone la imagen correspondiente al circuito seleccionado
     */
    fun generarNivel(){
        generarValores()
        generaCircuitos()
        circuito = generaCircuitos()
        soluciones= arrayOf(circuito1(),circuito2(),circuito3())
        binding.circuitos.setImageDrawable(resources.getDrawable(ids.get(circuito)))
        //resources.getDrawable(ids.get(circuito),)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Genera valores aleatorios con una clase Random.
     * Luego los muestra en los textView Correspondientes
     */
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

    /**
     * Elige un circuto desde 0 hasta el tamaño del array de IDs de los circuitos posibles
     *
     * @return Número entero que indica el circuito seleccionado del array ids
     *
     */
    fun generaCircuitos():Int {
        return r.nextInt(0..ids.size-1)
    }

    /**
     * Funcion solución del circuito 1
     *
     * @return Boolean resultado del circuito
     */
    fun circuito1():Boolean{
        return (a != b) || !(b && c)
    }

    /**
     * Función solución del circuito 2
     *
     * @return Boolean resultado del circuito
     */
    fun circuito2():Boolean{
        return (a || b) != (b && c)
    }

    /**
     * Función solución del circuito 3
     *
     * @return Boolean resultado del circuito
     */
    fun circuito3():Boolean{
        return (a != b) && !(c || d)
    }

    /**
     * Función que tranforma un valor booleano a un entero
     * True -> 1 | False -> 0
     * @param b Boolean a transformar
     *
     * @return Int
     */
    fun booleanToInt(b: Boolean): Int {
        return if (b) 1 else 0
    }

    /**
    * Función que muestra el icono de acierto o error durante 2 segundos.
    * Primero se elige el icono, luego se hace visible y con un timer esperamos 2 segundos y lo hacemos invisible.
    *
    * @property s Booleano que nos indica si se acerto o fallo
     */
    fun solucion(s:Boolean){
        if(s){
            binding.solucion.setImageResource(R.drawable.ic_correct)
            HighscoreManager.addPoint(requireContext(), R.string.menu_ejercicioCircuitosDigitales)
        }else{
            binding.solucion.setImageResource(R.drawable.ic_incorrect)
            HighscoreManager.remPoint(requireContext(), R.string.menu_ejercicioCircuitosDigitales)
        }
        val resultado:ImageView = binding.solucion
        resultado.visibility=View.VISIBLE
        Timer("SettingUp", false).schedule(500) {
            resultado.visibility=View.INVISIBLE//Esperamos removeImage() //El image view desaparece a los 2 seg
        }

    }

}