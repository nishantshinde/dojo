package nishantshinde.dojo.disruptor;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import nishantshinde.dojo.disruptor.domain.LongEvent;
import nishantshinde.dojo.disruptor.producer.LongEventProducer;

public class LongEventMain {

	public static void main(String[] args) throws Exception {

		EventFactory factory = new LongEventFactory();
		int bufferSize= 32;
		long MAX_ITERATIONS = 5_000_000;
		
		// Construct the Disruptor with a SingleProducerSequencer
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, 
				bufferSize,
				Executors.newCachedThreadPool(),
				ProducerType.SINGLE, // Single producer
				new BlockingWaitStrategy());

		RingBuffer<LongEvent> ringBuffer = disruptor.start();
	
		LongEventProducer producer = new LongEventProducer(ringBuffer);

		Random random = new Random();
		
		Instant started = Instant.now();
		for(int i=0;i<MAX_ITERATIONS;i++) {
			producer.onData(random.nextLong());
		}
		Instant stopped = Instant.now();
		
		NumberFormat nf = new DecimalFormat("###,###,###,###");
		Duration duration = Duration.between(started, stopped);
		
		System.out.printf("Total time taken %d.%d seconds for %s iterations.",
				duration.getSeconds(),duration.getNano(), nf.format(MAX_ITERATIONS));
	}
}