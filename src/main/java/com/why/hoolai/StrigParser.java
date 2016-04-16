package com.why.hoolai;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class StrigParser {

    public static void main(String[] args) {
        String s = "(A(B)(C(D)(E))F)";
        ParenthesisContent pc = new ParenthesisContent(s);
        System.out.println(pc.getContent(1));
        System.out.println(pc.getContent(2));
        System.out.println(pc.getContent(3));
        System.out.println(pc.getContent(4));
        System.out.println(pc.getContent(5));
    }
    
    private static final class ParenthesisContent{
    	
    	private final Map<Integer, StringBuilder> contentMap = new HashMap<Integer, StringBuilder>();
    	private final LinkedList<StringBuilder> contentQueue = new LinkedList<StringBuilder>();
    	
    	public ParenthesisContent(String s){
    		parse(s);
    	}
    	
    	public void parse(String s){
    		char[] charArray = s.toCharArray();
            int leftParenthesis = 0, rightParenthesis = 0;
            
            Stack<Integer> countStack = new Stack<Integer>();
            for(int i=0;i<charArray.length;i++){
                if(charArray[i] == '('){
                	leftParenthesis++;
                	collectContent(charArray[i]);
                    contentQueue.addFirst(new StringBuilder());
                    countStack.push(leftParenthesis);
                }else if(charArray[i] == ')'){
                	rightParenthesis++;
                	contentMap.put(countStack.pop(), contentQueue.removeFirst());
                	collectContent(charArray[i]);
                }else{
                	collectContent(charArray[i]);
                }
                
            }
            
            if(leftParenthesis != rightParenthesis){
                throw new IllegalArgumentException("括号没有成对出现！");
            }
    	}
    	
    	private void collectContent(char currentChar){
    		Iterator<StringBuilder> it = contentQueue.iterator();
        	while(it.hasNext()){
        		StringBuilder sb = it.next();
        		sb.append(currentChar);
        	}
    	}
    	
    	public String getContent(int index){
    		return contentMap.get(index).toString();
    	}
    	
    }
    
}
