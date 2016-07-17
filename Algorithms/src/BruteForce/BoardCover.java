package BruteForce;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardCover{

	public static int N;
	public static int M;
	//public static int areFriends[][];
//	public static int[][][] shape = new int[4][3][2];
	public static int[][][] shape = {
			{{0,0}, {1,0}, {0,1}},
			{{0,0}, {0,1}, {1,1}},
			{{0,0}, {1,0}, {1,1}},
			{{0,0}, {1,0}, {1,-1}},
			
	};
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\BruteForce\\BoardCover.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		for(int k=0; k<T; k++){
			N=scan.nextInt();
			M=scan.nextInt(); 
			scan.nextLine();
			
			int possiblecnt = 0;
			
			int[][] board = new int[N][M];
//			char[] taken = new char[N]; 
			
			for(int i=0; i<N; i++){
				String line = scan.nextLine();
				for(int j=0; j<M; j++){
					char temp = line.charAt(j);
					if(temp == '#'){
						board[i][j] = 1;
					}
					if(temp == '.'){
						board[i][j] = 0;
						possiblecnt++;
					}	
				}
			}
//			for(int i=0; i<N; i++){
//				for(int j=0; j<M; j++){
//					//System.out.println("i: " + i+" j: " + j);
//					System.out.println("board["+i+"]["+j+"]= " + board[i][j]);
//				}
//			}
			
			
			long start = System.currentTimeMillis();
			
						
			int result = 0;
			
			if(possiblecnt%3 != 0){
				result = -1;
			}else{
				result = check(board);
			}
			
		    long end = System.currentTimeMillis();
		    //System.out.println((end - start)/(double)1000);
			System.out.println("result : " + result);
			System.out.println();
		}
	}
	private static int check(int[][] board) {
	
		int result=0;
		
		int x=-1;
		int y=-1;
		
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(board[i][j] == 0){
					x = i;
					y = j;
					//System.out.println("first : " + x + " "+ y);
					break;
				}
			}
			if(x != -1){
				break;
			}
		}
		if(x == -1){
			return 1;
		}
		
		for(int type=0; type<4; type++){
			
			if(setblock(board,x,y,type,1)){
				//System.out.println("check inside");
				result += check(board);
			}
			setblock(board,x,y,type,-1);
		}
		
		return result;
	}
	private static boolean setblock(int[][] board, int x, int y, int type, int input) {
		
		boolean ok = true;
		
		for(int i =0; i <3; i++){
			//System.out.println("N: "+ N + "M: "+ M + "i: "+ i);
			int tempx = x + shape[type][i][0];
			int tempy = y + shape[type][i][1];
			
			if(tempx>=N || tempx<0 || tempy<0 || tempy >=M){
				//System.out.println("out of board");
				ok = false;
			}else{
				//System.out.println("input check");
				board[tempx][tempy] += input;
				if(board[tempx][tempy] > 1){
					ok = false;
				}
				//System.out.println(ok);
			}
		}
		
//		if(ok==true){
//			System.out.println("true now");
//		}
		return ok;
	}
}

