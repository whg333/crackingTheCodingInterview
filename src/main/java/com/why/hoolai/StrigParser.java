package com.why.hoolai;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class StrigParser {

    public static void main(String[] args) {
        String s = "(A(B)(C(D)(E))F)";
//        ParenthesisContent pc = new ParenthesisContent(s);
//        System.out.println(pc.getContent(1));
//        System.out.println(pc.getContent(2));
//        System.out.println(pc.getContent(3));
//        System.out.println(pc.getContent(4));
//        System.out.println(pc.getContent(5));
        
        ParenthesisContentOnce pco = new ParenthesisContentOnce();
        System.out.println(pco.getContent(s, 1));
        System.out.println(pco.getContent(s, 2));
        System.out.println(pco.getContent(s, 3));
        System.out.println(pco.getContent(s, 4));
        System.out.println(pco.getContent(s, 5));
    }
    
    /** 可以不必全部遍历完成，当找到指定index后直接返回对应栈中的内容 */
    private static final class ParenthesisContentOnce{
        
        public String getContent(String s, int index){
            char[] charArray = s.toCharArray();
            int leftParenthesis = 0, rightParenthesis = 0;
            LinkedList<IndexContent> contentQueue = new LinkedList<IndexContent>();
            
            for(int i=0;i<charArray.length;i++){
                char currentChar = charArray[i];
                if(currentChar == '('){
                    leftParenthesis++;
                    collectContent(contentQueue, currentChar);
                    contentQueue.addFirst(new IndexContent(leftParenthesis));
                }else if(currentChar == ')'){
                    rightParenthesis++;
                    IndexContent indexContent = contentQueue.removeFirst();
                    if(index == indexContent.index){
                        //System.out.println("Found!");
                        return indexContent.content.toString();
                    }
                    collectContent(contentQueue, currentChar);
                }else{
                    collectContent(contentQueue, currentChar);
                }
                
            }
            
            if(leftParenthesis != rightParenthesis){
                throw new IllegalArgumentException("括号没有成对出现！");
            }
            
            return "No Found!";
        }
        
        /** 针对所有在栈内的元素都做收集内容的操作 */
        private void collectContent(LinkedList<IndexContent> contentQueue, char currentChar){
            Iterator<IndexContent> it = contentQueue.iterator();
            while(it.hasNext()){
                it.next().collect(currentChar);
            }
        }
        
    }
    
    /** 全部遍历完成后构造的一个index --> content的键值映射，便于下次根据index直接getContent出来 */
    private static final class ParenthesisContent{
    	
    	private final Map<Integer, StringBuilder> contentMap = new HashMap<Integer, StringBuilder>();
    	private final LinkedList<IndexContent> contentQueue = new LinkedList<IndexContent>();
    	
    	public ParenthesisContent(String s){
    		parse(s);
    	}
    	
    	public void parse(String s){
    		char[] charArray = s.toCharArray();
            int leftParenthesis = 0, rightParenthesis = 0;
            
            for(int i=0;i<charArray.length;i++){
                char currentChar = charArray[i];
                if(currentChar == '('){
                	leftParenthesis++;
                	collectContent(currentChar);
                    contentQueue.addFirst(new IndexContent(leftParenthesis));
                }else if(currentChar == ')'){
                	rightParenthesis++;
                	IndexContent indexContent = contentQueue.removeFirst();
                	contentMap.put(indexContent.index, indexContent.content);
                	collectContent(currentChar);
                }else{
                	collectContent(currentChar);
                }
                
            }
            
            if(leftParenthesis != rightParenthesis){
                throw new IllegalArgumentException("括号没有成对出现！");
            }
    	}
    	
    	/** 针对所有在栈内的元素都做收集内容的操作 */
    	private void collectContent(char currentChar){
    		Iterator<IndexContent> it = contentQueue.iterator();
        	while(it.hasNext()){
        		it.next().collect(currentChar);
        	}
    	}
    	
    	public String getContent(int index){
    		return contentMap.get(index).toString();
    	}
    	
    }
    
    private static class IndexContent{
        private final int index;
        private final StringBuilder content = new StringBuilder();
        public IndexContent(int index) {
            this.index = index;
        }
        public void collect(char c){
            content.append(c);
        }
    }
    
}
