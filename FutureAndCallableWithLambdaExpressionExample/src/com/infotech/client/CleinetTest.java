package com.infotech.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CleinetTest {

	public static void main(String[] args) {

		Callable<String> task = ()->"My task is done";
		
		int number =10;
		Callable<Integer> getSumOfEvenNumbers =()->{
			int sum = 0;
			for (int i = 1; i <=number; i++) {
				if(i%2== 0){
					sum = sum+i;
				}
			}
			return sum;
		};
		
		Callable<Integer> getSumOfOddNumbers =()->{
			int sum = 0;
			for (int i = 1; i <=number; i++) {
				if(i%2== 1){
					sum = sum+i;
				}
			}
			return sum;
		};
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		Future<String> future1 = executorService.submit(task);
		Future<Integer> future2 = executorService.submit(getSumOfEvenNumbers);
		Future<Integer> future3 = executorService.submit(getSumOfOddNumbers);
		
		try {
			 System.out.println(future1.get());
			 System.out.println("Sum of Even Nos:"+future2.get(10, TimeUnit.SECONDS));
			 System.out.println("Sum of Odd Nos:"+future3.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}

}
