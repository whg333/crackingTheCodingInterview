package com.why.hoolai;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StrigParser {

    public static void main(String[] args) {
        String s = "(A(B)(C(D)(E))F)";
        System.out.println(getContent(s, 1));
    }
    
    private static String getContent(String s, int index){
        char[] charArray = s.toCharArray();
        int leftParenthesis = 0;
        boolean collect = false;
        Stack<String> stack = new Stack<String>();
        Map<Integer, String> contentMap = new HashMap<Integer, String>();
        for(int i=0;i<charArray.length;i++){
            if(charArray[i] == '('){
                leftParenthesis++;
                collect = true;
            }else if(charArray[i] == ')'){
                contentMap.put(leftParenthesis--, stack.pop());
            }else if(collect){
                stack.push(charArray[i]+"");
            }
        }
        if(leftParenthesis != 0){
            throw new IllegalArgumentException("括号没有成对出现！");
        }
        return contentMap.get(index);
    }
    
}
