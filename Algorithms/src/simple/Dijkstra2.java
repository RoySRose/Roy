package simple;

import java.io.IOException;
import java.util.Scanner;

public class Dijkstra2 {

	static int path[][];
	static int dis[];
	static int Q[];
	static int n;
	static int max = 214748364;
	
	public static void init(){
		for(int i=1; i<=n; i++){
			dis[i] = max;
			Q[i]=i;
		}
	}
	
	public static int min(){
		int m = max;
		int t = 0;
		for(int i=1; i<=n; i++){
			if(Q[i] != -1 && m > dis[Q[i]]){
				m = dis[Q[i]];
				t=i;
			}
		}
		return t;
	}
	
	public static Boolean chkEmpty(){
		for(int i=1; i<=n; i++){
			if(Q[i]>0){
				return false;
			}
		} 
		return true;
	}
	public static void main(String[] args)throws IOException{
		int a,b,c,s,e,cnt;
		
		Scanner sc = new Scanner(System.in);
		
		cnt = sc.nextInt();
		n = sc.nextInt();
		s = sc.nextInt();
		e = sc.nextInt();
		
		path = new int[cnt+1][cnt+1];
		dis = new int[n+1];
		Q = new int [n+1];
		
		for(int i =1; i <= cnt; i ++){
			a=sc.nextInt();
			b=sc.nextInt();
			c=sc.nextInt();
			
			path[a][b] = c;
			//System.out.println("in  " + i + ": a=" + a+ ", b=" +b);
		}
		
		init();
		dis[s] =0;
		
		while(!chkEmpty()){
			int m = min();
			if(m==0){
				break;
			}
			Q[m] = -1;
			for(int i=1; i<=n; i++){
				int d = dis[m] + path[m][i];
				if(d < dis[i] && d!=0 && path[m][i]!=0){
					dis[i] = d;
				}
			}
		}
		
		System.out.println(dis[e]);
	}
	
	//sample
	//9
	//6
	//	1 6
	//	1 2 10
	//	1 3 30
	//	1 4 15
	//	2 5 20
	//	3 6 5
	//	4 3 5
	//	4 6 20
	//	5 6 20
	//	6 4 20
	//	result :25
	
}
