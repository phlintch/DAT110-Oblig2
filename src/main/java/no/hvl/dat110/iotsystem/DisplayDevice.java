package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");

		Client display = new Client("Display", Common.BROKERHOST, Common.BROKERPORT);
		display.connect();
		display.createTopic(Common.TEMPTOPIC);
		display.subscribe(Common.TEMPTOPIC);
		for (int i = 0; i < COUNT; i++) {
			PublishMsg msg = (PublishMsg) display.receive();
			System.out.println("Display: "+msg.getMessage());
		}
		display.unsubscribe(Common.TEMPTOPIC);
		display.disconnect();

		System.out.println("Display stopping ... ");

		
	}
}
