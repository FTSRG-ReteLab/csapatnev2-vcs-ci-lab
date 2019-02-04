package hu.bme.mit.train.tachograph;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainTachographTest {

	TrainTachograph tachograph;
	
	@Before
	public void before() {
		tachograph = new TrainTachograph();
	}
	
	@Test
	public void TrainTachograph_isEmpty() {
		Assert.assertEquals(true, tachograph.isEmpty());
		
		tachograph.record(new Date(), 2, 10);
		Assert.assertEquals(false, tachograph.isEmpty());
	}

	
}