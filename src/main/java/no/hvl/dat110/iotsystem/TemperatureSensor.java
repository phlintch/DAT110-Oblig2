package no.hvl.dat110.iotsystem;

import java.util.Random;

public class TemperatureSensor {

	private static final int RANGE = 20;

	public int read() {

		long seconds = System.currentTimeMillis();

		//double temp = RANGE * Math.sin(seconds / 1000);
		Random rn = new Random();
		double temp = rn.nextInt(40) - RANGE;
		return (int) Math.ceil(temp);
	}
}
