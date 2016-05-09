package nishantshinde.dojo.kryo;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class KryoClient {

	Client client;
	String serverIpAddress;
	Integer kryoServerTcpPort;
	Integer kryoServerUdpPort;
	
	public KryoClient(Integer kryoServerTcpPort) {
		this.client = new Client();
		this.kryoServerTcpPort = kryoServerTcpPort;
		KryoHelper.registerClassesWithKryo(client.getKryo());
	}

	public KryoClient(String serverIpAddress, Integer kryoServerTcpPort, Integer kryoServerUdpPort) {
		this.client = new Client();
		this.serverIpAddress = serverIpAddress;
		this.kryoServerTcpPort = kryoServerTcpPort;
		this.kryoServerUdpPort = kryoServerUdpPort;
		KryoHelper.registerClassesWithKryo(client.getKryo());
	}
	
	public void start() {
		client.start();

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

	private void connectToConfiguredServer(String serverIpAddress, int kryoServerTcpPort, int kryoServerUdpPort) {
		try {
			client.connect(1000, serverIpAddress, kryoServerTcpPort, kryoServerUdpPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void autoConnectToAnyAvailableServer() {
		
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
