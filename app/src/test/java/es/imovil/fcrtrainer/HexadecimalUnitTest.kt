package es.imovil.fcrtrainer

import es.imovil.fcrtrainer.ui.codes.hexadecimal.HexadecimalFragment
import org.junit.Assert
import org.junit.Test

class HexadecimalUnitTest {

    private var hexFrag: HexadecimalFragment = HexadecimalFragment()

    @Test
    fun titleString() {
        Assert.assertEquals("Convierte de binario con 8 bits a hexadecimal", hexFrag.titleString())
    }

    @Test
    fun numberOfBits() {
        Assert.assertEquals(8,hexFrag.numberOfBits())
    }

    @Test
    fun obtainSolution() {
        Assert.assertEquals("0", hexFrag.obtainSolution())
    }

    @Test
    fun generateRandomNumber() {
        Assert.assertNull(hexFrag.generateRandomNumber())
    }

    @Test
    fun isCorrect() {
        Assert.assertEquals(true, hexFrag.isCorrect("0"))
    }
}