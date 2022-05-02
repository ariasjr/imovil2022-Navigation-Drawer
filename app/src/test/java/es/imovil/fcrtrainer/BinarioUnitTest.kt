package es.imovil.fcrtrainer

import android.view.LayoutInflater
import es.imovil.fcrtrainer.ui.codes.binario.BinarioFragment
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BinarioUnitTest {
    //private var binFrag: BinarioFragment = BinarioFragment()

    //Probamos la generacion de numeros de forma aleatoria
    @Test
    fun generateRandomNumber() {
        var binFrag: BinarioFragment = BinarioFragment()
        Assert.assertNotNull(binFrag.generateRandomNumber())
    }

    //Comprobamos que el numero de bits es 6 (el que pusimos de primeras)
    @Test
    fun numberOfBits() {
        var binFrag: BinarioFragment = BinarioFragment()
        Assert.assertEquals(6.0, binFrag.numberOfBits(),1e-15)
    }
    //Probamos el modo 1 binario -> decimal
    @Test
    fun obtainSolution() {
        var binFrag: BinarioFragment = BinarioFragment()
        binFrag.setNumbertoConvert(5);
        Assert.assertEquals("101", binFrag.obtainSolution())
    }

    @Test
    fun isCorrect() {
        var binFrag: BinarioFragment = BinarioFragment()
        binFrag.setNumbertoConvert(5);
        Assert.assertEquals(true, binFrag.isCorrect("101"))
    }

    //Probamos el modo 2: decimal -> binario
    @Test
    fun isCorrect2() {
        var binFrag: BinarioFragment = BinarioFragment()
        binFrag.changeMode(true)
        binFrag.setNumbertoConvert(101);
        Assert.assertEquals(true, binFrag.isCorrect("101"))
    }
    @Test
    fun obtainSolution2() {
        var binFrag: BinarioFragment = BinarioFragment()
        binFrag.changeMode(true)
        binFrag.setNumbertoConvert(101);
        Assert.assertEquals("101", binFrag.obtainSolution())
    }





}