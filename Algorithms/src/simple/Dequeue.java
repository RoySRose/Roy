package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Dequeue {

	public static void main(String [] args){
		
		
		PriorityQueue<Integer> dq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		dq.add(1);
		dq.add(3);
		dq.add(2);
		dq.add(4);
		
		
		
		System.out.println(dq);
		
		System.out.println("dqsize " + dq.size());
		int k = dq.size();
		for(int i=0; i<k; i++){
			System.out.println("i=" + i);
			System.out.println(dq.poll());
		}
	}
}
