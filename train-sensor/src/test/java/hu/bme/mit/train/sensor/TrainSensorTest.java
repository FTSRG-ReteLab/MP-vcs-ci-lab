package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.mockito.Mockito;


public class TrainSensorTest {
	
	TrainController mockController;
	TrainUser mockUser;
	
	TrainSensor ts;
	
	@Before
	public void TrainSensorTestInit() {
		mockController = Mockito.mock(TrainController.class);
		mockUser = Mockito.mock(TrainUser.class);
		
		
		ts = new TrainSensorImpl(mockController, mockUser);
	}
	
	@Test
	public void NormalSpeedLimit() {
		ts.overrideSpeedLimit(20);
		ts.overrideSpeedLimit(15);
		Mockito.verify(mockUser, Mockito.times(0)).setAlarmState(true);
		
	}
	
	@Test
	public void TooFast() {
		ts.overrideSpeedLimit(499);
		ts.overrideSpeedLimit(500);
		ts.overrideSpeedLimit(501);
		
		Mockito.verify(mockUser, Mockito.times(1)).setAlarmState(true);
	
	}
	
	@Test
	public void TooSlow() {
		ts.overrideSpeedLimit(-1);
		
		Mockito.verify(mockUser, Mockito.times(1)).setAlarmState(true);
	
	}
	
	@Test
	public void TooFastDeceleration() {
		ts.overrideSpeedLimit(100);
		ts.overrideSpeedLimit(80);
		ts.overrideSpeedLimit(20);
		
		
		Mockito.verify(mockUser, Mockito.times(1)).setAlarmState(true);
	
	}
}
