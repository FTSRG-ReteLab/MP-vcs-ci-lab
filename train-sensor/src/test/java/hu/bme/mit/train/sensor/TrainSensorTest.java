package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
	
	TrainController controller;
	TrainSensor sensor;
	TrainUser user;

    @Before
    public void before() {
        TrainSystem system = new TrainSystem();
	controller = system.getController();
	sensor = system.getSensor();
	user = system.getUser();
    }

    @Test
    public void ThisIsATestStub() {
	Assert.assertEquals(5,sensor.getSpeedLimit());
	sensor.overrideSpeedLimit(-1);
	Assert.assertEquals(5,sensor.getSpeedLimit());
    }
}
