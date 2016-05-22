package nishantshinde.dojo.disruptor.consumer;

import com.lmax.disruptor.EventHandler;

import nishantshinde.dojo.disruptor.domain.LongEvent;
import nishantshinde.dojo.disruptor.logging.MessageLogger;

public class LongEventHandler implements EventHandler<LongEvent> {
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
		MessageLogger.logMessage(String.format("LongEventHandler.onEvent() called with event = %s & sequence = %l ", event.toString(), sequence));
	}
}