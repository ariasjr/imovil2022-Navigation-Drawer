package es.imovil.fcrtrainer

import es.imovil.fcrtrainer.ui.codes.twosComplement.TwosComplementFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TwosComplementUnitTest {

    @Test
    fun generateRandomNumber() {
        var tcFrag = TwosComplementFragment()
        assertNotNull(tcFrag.generateRandomNumber())
    }

    @Test
    fun twosComplement() {
        var tcFrag = TwosComplementFragment()
        var tc = tcFrag.twosComplement(5);
        assertEquals("0101", tc)
        tc = tcFrag.twosComplement(-6);
        assertEquals("1010", tc)
    }

}