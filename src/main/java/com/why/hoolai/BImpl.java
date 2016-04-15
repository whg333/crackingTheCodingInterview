package com.why.hoolai;

public class BImpl implements B {

    @Override
    public A getA() {
        return new A(){
            @Override
            public String foo() {
                return "Hello\n"+new AImpl().foo()+"\nExpect for your cooperation";
            }
        };
    }
    
    public static void main(String[] args) {
        System.out.println(new BImpl().getA().foo());
    }

}
