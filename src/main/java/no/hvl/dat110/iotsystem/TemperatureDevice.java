package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		Client sensor = new Client("Sensor", Common.BROKERHOST, Common.BROKERPORT);
		sensor.connect();
		for (int i = 0; i < COUNT; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			String msg = "" + sn.read();
			System.out.println("Reading: "+msg);
			sensor.publish(Common.TEMPTOPIC, msg);
		}
		sensor.disconnect();
		System.out.println("Temperature device stopping ... ");



	}
}
