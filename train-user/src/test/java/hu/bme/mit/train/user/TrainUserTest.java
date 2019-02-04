package hu.bme.mit.train.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

public class TrainUserTest {

	TrainController controller;
	TrainUser user;
	
	@Before
	public void before() {
		controller = new TrainController() {
            @Override
            public void followSpeed() {}

            @Override
            public int getReferenceSpeed() {
                return 0;
            }

            @Override
            public void setSpeedLimit(int speedLimit) {}

            @Override
            public void setJoystickPosition(int joystickPosition) {}
        };
		user = new TrainUserImpl(controller);
	}
	
	@Test
	public void TestAlarmFlag() {
        user.overrideJoystickPosition(0);
        Assert.assertEquals(false, user.getAlarmFlag());
        user.overrideJoystickPosition(10);
        Assert.assertEquals(true, user.getAlarmFlag());
	}

	
}
