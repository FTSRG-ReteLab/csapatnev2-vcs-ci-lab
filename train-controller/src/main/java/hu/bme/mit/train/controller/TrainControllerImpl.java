package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Timer;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	private Thread timer;

	public TrainControllerImpl() {
		timer = new TimerThread(this);
		timer.start();
	}

	@Override
	public synchronized void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public synchronized int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public synchronized void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public synchronized void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	private static class TimerThread extends Thread {
		TrainController controller;

		TimerThread(TrainController controller) {
			this.controller = controller;
		}

		@Override
		public void run() {
			while(true) {
				controller.followSpeed();
				try {
					sleep(500);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
