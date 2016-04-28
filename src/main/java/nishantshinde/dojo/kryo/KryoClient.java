package nishantshinde.dojo.kryo;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoClient {

	Client client;
	public KryoClient() {
		client = new Client();
		KryoHelper.registerClassesWithKryo(client.getKryo());
	}
	
	public void start() {
		client.start();
		try {
			client.connect(1000, "10.109.20.48", 51555, 51777);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    client.addListener(new Listener() {
	        public void received (Connection connection, Object object) {
		           if (object instanceof String) {
		        	  String response = (String)object;
		              System.out.println("Client received a response String - " +response);
		           } else  if (object instanceof Message) {
		        	   Message response = (Message)object;
		              System.out.println("Client received a response Message - " +response);
		           }
	        }
	     });
	}

	public void send(String message) {
		System.out.println("Client sending String - " + message);
		client.sendTCP(message);
	}

	public void send(Message message) {
		System.out.println("Client sending Message - " + message);
		client.sendTCP(message);		
	}
	
	public void stop() {
		client.stop();
	}
	
}
