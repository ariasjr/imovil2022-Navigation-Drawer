package es.imovil.fcrtrainer

import es.imovil.fcrtrainer.ui.digital.ejercicioCircuitosDigitales.FragmentExerciceCircuitosDigitales
import org.junit.Assert
import org.junit.Test

class CircuitosDigitalesTest {

    @Test
    fun generarNivel(){
        var cdFrag: FragmentExerciceCircuitosDigitales = FragmentExerciceCircuitosDigitales()
        Assert.assertNotNull(cdFrag.generarNivel())
    }

    @Test
    fun generarValores(){
        var cdFrag: FragmentExerciceCircuitosDigitales = FragmentExerciceCircuitosDigitales()
        Assert.assertNotNull(cdFrag.generarValores())
    }

    @Test
    fun generaCircuitos(){
        var cdFrag: FragmentExerciceCircuitosDigitales = FragmentExerciceCircuitosDigitales()
        Assert.assertNotNull(cdFrag.generaCircuitos())
    }

    @Test
    fun circuito1(){
        var cdFrag: FragmentExerciceCircuitosDigitales = FragmentExerciceCircuitosDigitales()
        Assert.assertNotNull(cdFrag.circuito1())
    }

    @Test
    fun circuito2(){
        var cdFrag: FragmentExerciceCircuitosDigitales = FragmentExerciceCircuitosDigitales()
        Assert.assertNotNull(cdFrag.circuito2())
    }

    @Test
    fun circuito3(){
        var cdFrag: FragmentExerciceCircuitosDigitales = FragmentExerciceCircuitosDigitales()
        Assert.assertNotNull(cdFrag.circuito3())
    }
}