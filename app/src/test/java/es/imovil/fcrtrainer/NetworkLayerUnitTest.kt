package es.imovil.fcrtrainer

import es.imovil.fcrtrainer.ui.networks.networklayer.NetworkLayerFragment
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NetworkLayerUnitTest {

    private var networkLayerFragment=NetworkLayerFragment()

    @Test
    fun titleString(){
        Assert.assertEquals("Adivina la capa", networkLayerFragment.titleString())
    }

    @Test
    fun calculateNumber(){
        assertEquals(2, networkLayerFragment.calculateNumber())
    }
}