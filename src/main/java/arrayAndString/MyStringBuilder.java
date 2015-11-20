package arrayAndString;

import java.util.ArrayList;
import java.util.List;

public class MyStringBuilder implements IStringBuilder, CharSequence{

	private final List<Character> content = new ArrayList<Character>();
	
	public MyStringBuilder(){
	}
	
	public MyStringBuilder(String s){
		addStr(s);
	}
	
	public MyStringBuilder(List<Character> s) {
		content.addAll(s);
	}

	@Override
	public int length() {
		return content.size();
	}

	@Override
	public char charAt(int index) {
		return content.get(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return new MyStringBuilder(content.subList(start, end));
	}

	@Override
	public MyStringBuilder append(CharSequence cs) {
		addStr(cs.toString());
		return this;
	}
	
	private void addStr(String s){
		for(int i=0;i<s.length();i++){
			content.add(s.charAt(i));
		}
	}
	
	@Override
	public MyStringBuilder append(char c) {
		content.add(c);
		return this;
	}
	
	@Override
	public String toString(){
		char[] chars = new char[content.size()];
		for(int i=0;i<chars.length;i++){
			chars[i] = content.get(i);
		}
		return new String(chars);
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("my ").append("name ").append('i').append('s').append(" whg").append('!');
		System.out.println(sb);
		sb = new StringBuilder("haha");
		sb.append("").append("").append(" ~~").append('是').append(" oo!").append("").append(' ').append("");
		System.out.println(sb+"-end");
		
		MyStringBuilder msb = new MyStringBuilder();
		msb.append("my ").append("name ").append('i').append('s').append(" whg").append('!');
		System.out.println(msb);
		msb = new MyStringBuilder("haha");
		msb.append("").append("").append(" ~~").append('是').append(" oo!").append("").append(' ').append("");
		System.out.println(msb+"-end");
	}
	
}
