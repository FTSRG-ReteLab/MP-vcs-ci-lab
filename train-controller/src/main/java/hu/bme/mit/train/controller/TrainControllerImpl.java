package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Timer;
import java.util.TimerTask;

import com.google.common.collect.ImmutableTable;


public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private ImmutableTable<Integer,Integer,Integer> table;
	
	public TrainControllerImpl(){
		table=new ImmutableTable.Builder<Integer, Integer, Integer>()
		           .put(1, 1, 0)
		           .put(1, 0, 0)
		           .put(2, 1, 0)
		           .build();
	}
	
	public int getTableValue(int r,int c){
		return table.get(r,c);	
	}
	
	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
                referenceSpeed += step;
		}
		enforceSpeedLimit();
	}
	
	public void startController() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			  @Override
			  public void run() {
			    followSpeed();
			  }
			}, 100, 100);
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
