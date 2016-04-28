package nishantshinde.dojo.kryo;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.esotericsoftware.minlog.Log;

public class TestKryo {
	
	public static void main(String[] args) {
		
		Log.set(Log.LEVEL_TRACE);
		final KryoServer kryoServer = new KryoServer();
		final KryoClient kryoClient = new KryoClient(); 
		
		System.out.println("Main thread starting kryo server & client ...");
		kryoServer.start();
		kryoClient.start();

		final AtomicBoolean stopFlag = new AtomicBoolean(false);
		
		System.out.println("Press ENTER to CONTINUE and then press ENTER again to STOP ...");
		new Scanner(System.in).nextLine();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1;i<1000;i++) {
					if(stopFlag.get()) {
						break;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//					kryoClient.send(new Message(i, "Message : " + i));
					kryoClient.send("Message : " + i);
				}
				
			}
		});
		t1.start();
		
		new Scanner(System.in).nextLine();
		System.out.println("Main thread trying to stop kryo client & server ...");
		stopFlag.set(true);
		kryoClient.stop();
		kryoServer.stop();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main thread exiting.");
	}
}
