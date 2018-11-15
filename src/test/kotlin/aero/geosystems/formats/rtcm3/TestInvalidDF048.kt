package aero.geosystems.formats.rtcm3

/*
 * Created by aimozg on 17.11.2017.
 * Confidential unless published on GitHub
 */

import aero.geosystems.formats.rtcm3.messages.Rtcm1012
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestInvalidDF048 {
	@Test fun f() {

		// RTCM1012,0,49054000,false,10,false,0;
		val m1012 = Rtcm1012.allocate(2).also {
			it.num_sat = 2
			// 12,0,6,509642.38,6.7685,127,23383811.724,38.25,2,-163.84,-262.1440,0,0.00;
			it.sats[0].apply {
				sat_id = 12
				l1code_ind = 0
				sat_freq_idx = 6
				l1psr = 509642.38
				l1phrl1psr = 6.7685
				l1locktime_ind = 127
				l1psr_amb = 23383811.724
				l1cnr = 38.25
				l2code_ind = 2
				l2l1psrdiff = -163.84
				l2phrl1psr = -262.1440
				l2locktime_ind = 0
				l2cnr = 0.00
			}
			// 19,0,10,12057.18,-2.9040,127,23383811.724,44.25,2,4.18,-6.7080,127,40.25;
			it.sats[1].apply {
				sat_id = 19
				l1code_ind = 0
				sat_freq_idx = 10
				l1psr = 12057.18
				l1phrl1psr = -2.9040
				l1locktime_ind = 127
				l1psr_amb = 23383811.724
				l1cnr = 44.25
				l2code_ind = 2
				l2l1psrdiff = 4.18
				l2phrl1psr = -6.7080
				l2locktime_ind = 127
				l2cnr = 40.25
			}
		}

		assertFalse(m1012.sats[0].isL2codeValid())
		assertTrue(m1012.sats[1].isL2codeValid())
	}
}