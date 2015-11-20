package whg.util;

public class LongBit {

	private static final int SIZE = 64;
	private static final long MASK_FLAG = 1L;
	private long[] bits;
	
	public LongBit(int size){
		bits = new long[size];
	}
	
	public boolean isOn(int index){
		int bitsIndex = index/SIZE;
		index = index - SIZE*bitsIndex;
		long mask = MASK_FLAG << index;
		return (bits[bitsIndex] & mask) == mask;
	}
	
	public void on(int index){
		int bitsIndex = index/SIZE;
		index = index - SIZE*bitsIndex;
		long mask = MASK_FLAG << index;
		bits[bitsIndex] |= mask;
	}
	
}
