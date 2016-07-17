package DynaminProgramming;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Packing{
	
	public static int n;
	public static int[] volume, need;
	public static int[][] cache = new int[1001][100];
	public static ArrayList<Integer> picked = new ArrayList<Integer>();
	
	public static void main(String [] args) throws IOException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\Packing.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//Scanner scan = new Scanner(System.in);
		//int T=scan.nextInt();
		
		int T =Integer.parseInt(br.readLine());
		String[] sp;
		String[] sp1;
				
		for(int k=0; k<T; k++){
			picked.clear();
			sp = br.readLine().split(" ");

			n = Integer.parseInt(sp[0]);
			int capacity = Integer.parseInt(sp[1]);
			volume = new int[n];
			need = new int[n];
			initcache();
				
			for(int i=0; i<n ;i++){
				sp1 = br.readLine().split(" ");
				volume[i] = Integer.parseInt(sp1[1]);
				need[i] = Integer.parseInt(sp1[2]);
			}
		
			//int ret = pack(capacity, 0);
			//System.out.println("ret : " + ret);
//			long start = System.currentTimeMillis();
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			reconstruct(capacity, 0);
			System.out.println(picked.toString());
		}
	}

	private static void reconstruct(int capacity, int item) {
		if(item==n)
			return;
		
		if(pack(capacity,item) == pack(capacity, item+1)){
			reconstruct(capacity, item+1);
		}else{
			picked.add(item);
			reconstruct(capacity- volume[item], item+1);
		}
		
	}

	private static void initcache() {
		
		for(int i=0; i< 1001; i++){
			for(int j=0; j<100; j++){
				cache[i][j] = -1;
			}
		}
		
	}

	private static int pack(int capacity, int item) {

		if(item == n){
			return 0;
		}
		int ret = cache[capacity][item];
		if(ret != -1) return ret;
		ret = cache[capacity][item] = pack(capacity, item+1);
		
		if(capacity >= volume[item]){
			ret = cache[capacity][item] = max(ret,pack(capacity-volume[item], item+1)+need[item]);
		}
		
		return ret;
	}
	
	private static int max(int a, int b){
		if(a >= b){
			return a;
		}else{
			return b;
		}
				
	}
	
}

