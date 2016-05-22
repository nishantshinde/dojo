package nishantshinde.dojo.disruptor.domain;

public class LongEvent {
	
	private long value;
	
	public void set(long value) {
		this.value = value;
	}
	
	public long get() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "LongEvent [value=" + value + "]";
	}
	
}