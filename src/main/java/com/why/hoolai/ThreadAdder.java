package com.why.hoolai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadAdder {

    public static void main(String[] args) {
        thread(new int[]{1, 2, 3});
        latch(new int[]{1, 2, 3});
    }
    
    private static void thread(final int[] shareArray){
        List<Thread> threads = new ArrayList<Thread>();
        for(int i=0;i<shareArray.length;i++){
            threads.add(new AddThread(shareArray, i));
        }
        for(Thread thread:threads){
            thread.start();
        }
        for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread(){
            @Override
            public void run() {
                int sum = 0;
                for(int i=0;i<shareArray.length;i++){
                    sum += shareArray[i];
                }
                System.out.println(sum);
            }
        }.start();
    }
    
    private static final class AddThread extends Thread{
        private final int[] shareArray;
        private final int index;
        public AddThread(int[] shareArray, int index) {
            this.shareArray = shareArray;
            this.index = index;
        }
        @Override
        public void run() {
            shareArray[index]++;
        }
    }
    
    private static void latch(final int[] shareArray){
        CountDownLatch latch = new CountDownLatch(shareArray.length);
        List<Thread> threads = new ArrayList<Thread>();
        for(int i=0;i<shareArray.length;i++){
            threads.add(new AddLatch(shareArray, i, latch));
        }
        threads.add(new SumLatch(shareArray, latch));
        
        for(Thread thread:threads){
            thread.start();
        }
    }
    
    private static final class AddLatch extends Thread{
        private final int[] shareArray;
        private final int index;
        private final CountDownLatch latch;
        public AddLatch(int[] shareArray, int index, CountDownLatch latch) {
            this.shareArray = shareArray;
            this.index = index;
            this.latch = latch;
        }
        @Override
        public void run() {
            shareArray[index]++;
            latch.countDown();
        }
    }
    
    private static final class SumLatch extends Thread{
        private final int[] shareArray;
        private final CountDownLatch latch;
        public SumLatch(int[] shareArray, CountDownLatch latch) {
            this.shareArray = shareArray;
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int sum = 0;
            for(int i=0;i<shareArray.length;i++){
                sum += shareArray[i];
            }
            System.out.println(sum);
        }
    }
    
}
