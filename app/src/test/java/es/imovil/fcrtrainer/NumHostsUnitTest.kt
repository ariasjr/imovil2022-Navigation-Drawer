package es.imovil.fcrtrainer

import es.imovil.fcrtrainer.ui.codes.num_hosts.NumHostsFragment
import org.junit.Assert.assertEquals
import org.junit.Test

class NumHostsUnitTest {

    private var numHostsFragment = NumHostsFragment()

    @Test
    fun testMaskIp(){
        assertEquals("0.0.0.0", numHostsFragment.intToIpString(0))
    }



}