package nishantshinde.dojo.disruptor;

import com.lmax.disruptor.EventFactory;

import nishantshinde.dojo.disruptor.domain.LongEvent;

public class LongEventFactory implements EventFactory<LongEvent> {
	public LongEvent newInstance() {
		return new LongEvent();
	}
}