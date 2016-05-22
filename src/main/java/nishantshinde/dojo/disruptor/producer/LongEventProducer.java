package nishantshinde.dojo.disruptor.producer;

import com.lmax.disruptor.RingBuffer;

import nishantshinde.dojo.disruptor.domain.LongEvent;
import nishantshinde.dojo.disruptor.logging.MessageLogger;

public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(Long longNumber) {
		MessageLogger.logMessage("LongEventProducer.onData() called with - " + longNumber);
		long sequence = ringBuffer.next(); // Grab the next sequence
		try {
			MessageLogger.logMessage("LongEventProducer.onData() trying to get event from ringbuffer for sequence - " + sequence);
			// Get the entry in the Disruptor for the sequence
			LongEvent event = ringBuffer.get(sequence);
//			Long longNumber = bb.getLong(0);
			event.set(longNumber); // Fill with data
		} finally {
			MessageLogger.logMessage("LongEventProducer.onData() publishing sequence - " + sequence);
			ringBuffer.publish(sequence);
		}
	}
}