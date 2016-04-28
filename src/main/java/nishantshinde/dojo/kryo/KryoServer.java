package nishantshinde.dojo.kryo;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class KryoServer {
	Server server;
	public KryoServer() {
		server = new Server();
		KryoHelper.registerClassesWithKryo(server.getKryo());
	}
	
	public void start() {
		System.out.println("KryoServer starting ... ");
		server.start();
		try {
			server.bind(51555, 51777);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    server.addListener(new Listener() {
	        public void received (Connection connection, Object object) {
	           if (object instanceof String) {
					String request = (String)object;
					System.out.println("Server received request String - " + request);
					String response = "You sent : " + request;
					connection.sendTCP(response);
	           } else  if (object instanceof Message) {
					Message request = (Message)object;
					System.out.println("Server received request Message - " + request);
					String response = "You sent : " + request;
					connection.sendTCP(response);
	           } else {
	        	   System.out.println("ERROR: Server received message of UNKNOWN type - " + object.getClass().getCanonicalName());
	           }
	        }
	     });
		
	}
	
	public void stop() {
		server.stop();
	}
}
