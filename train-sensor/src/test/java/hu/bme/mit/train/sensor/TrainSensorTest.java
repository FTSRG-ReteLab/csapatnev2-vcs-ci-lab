package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    private TrainController controller;
    private TrainUser user;
    private TrainSensor sensor;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void NewSpeedIsBelowAbsoluteMargin_ExpectedAlarmFlag() {
        sensor.overrideSpeedLimit(-20);
        verify(user).setAlarmFlag(true);
    }

    @Test
    public void NewSpeedIsAboveAbsoluteMargin_ExpectedAlarmFlag() {
        // Arrange

        // Act
        sensor.overrideSpeedLimit(600);

        // Assert
        verify(user).setAlarmFlag(true);
    }

    @Test
    public void NewSpeedIsBelowRelativeMargin_ExpectedAlarmFlag() {
        // Arrange
        when(controller.getReferenceSpeed()).thenReturn(150);

        // Act
        sensor.overrideSpeedLimit(50);

        // Assert
        verify(user).setAlarmFlag(true);
    }

    @Test
    public void NewSpeedIsInCorrectRange_NotExpectedAlarmFlag() {
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(100);
        verify(user).setAlarmFlag(false);
    }

    @Test
    public void GetSpeedLimitIsSameAsOverrideSpeedLimit() {
        int expected = 100;
        sensor.overrideSpeedLimit(expected);
        Assert.assertEquals(expected, sensor.getSpeedLimit());
    }
}
