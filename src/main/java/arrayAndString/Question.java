package arrayAndString;

import whg.util.LongBit;

public class Question {
	
	public static void main(String[] args) {
		System.out.println(isUniqueChar("abacdefg"));
	}

	private static boolean isUniqueChar(String s){
		if(s.length() <= 1){
			return true;
		}
		
		LongBit bit = new LongBit(2);
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(bit.isOn(c)){
				return false;
			}
			bit.on(c);
		}
		return true;
	}
	
}
