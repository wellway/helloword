package com.lock;


public class SynObj{
    public synchronized void showA(){
        try {
            Thread.sleep(3000);
            System.out.println("showA..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void showB(){
        synchronized (this) {
            System.out.println("showB..");
        }
    }
    
    public void showC(){
        String s="1";
        System.out.println(Thread.currentThread().getId());
        synchronized (s) {
        	 try {
        		 System.out.println("showC..");
                 Thread.sleep(2000);
                
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        	  System.out.println("unlock..."+Thread.currentThread().getId());
        }
      
    }
    
//    private String count;
    public void showD(String count){
        System.out.println(Thread.currentThread().getId());
        synchronized (count) {
        	 try {
        		 System.out.println("showdD..");
                 Thread.sleep(2000);
                
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        	  System.out.println("unlock..."+Thread.currentThread().getId());
        }
      
    }
}