package nishantshinde.dojo.kryo;

import com.esotericsoftware.kryo.Kryo;

public class KryoHelper {
	public static void registerClassesWithKryo(Kryo kryo){
		kryo.register(Message.class);
	}
}
