package whg.util;

public class IntBit {

	private static final int SIZE = 32;
	private static final int MASK_FLAG = 1;
	private int[] bits;
	
	public IntBit(int size){
		bits = new int[size];
	}
	
	public boolean isOn(int index){
		int bitsIndex = index/SIZE;
		index = index - SIZE*bitsIndex;
		int mask = MASK_FLAG << index;
		return (bits[bitsIndex] & mask) == mask;
	}
	
	public void on(int index){
		int bitsIndex = index/SIZE;
		index = index - SIZE*bitsIndex;
		int mask = MASK_FLAG << index;
		bits[bitsIndex] |= mask;
	}
	
}
