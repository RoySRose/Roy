package DynaminProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WildCardDP2 {

	public static int cache[][] = new int[101][101];
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\WildCard.txt"));

		//ArrayList<String> input = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);

		int T=scan.nextInt();
		
		for(int k=0; k<T; k++){
			
			System.out.println("Case : " + k);
			String pattern =scan.next();
			int wordcnt = scan.nextInt();
			String[] words =  new String[wordcnt];
			
			for(int m=0; m<wordcnt ; m++){
				words[m]=scan.next();	
			}
			
			long start = System.currentTimeMillis();
			
			for(int m=0; m<wordcnt ; m++){
				initcache();
				if(matchMemoized(0,0,pattern, words[m]) == 1){
					System.out.println("@@@pass@@@ " + words[m]);
				}
//				if(match(pattern,words[m])){
//					System.out.println("@@@pass@@@ " + words[m]);
//				}
			}

		    long end = System.currentTimeMillis();
		    System.out.println((end - start)/(double)1000);

//		    for(int i=0; i<101; i++){
//				for(int j=0; j<101; j++){
//					System.out.print(cache[i][j]);
//				}
//				System.out.println();
//			}
			
		}
	}

	private static int matchMemoized(int p, int w, String pattern, String word) {
		
		int ret = cache[p][w];
		if (ret!= -1) return ret;
		
		while(w<word.length() && p<pattern.length() && (pattern.charAt(p)=='?' || pattern.charAt(p) == word.charAt(w))){
			return ret = cache[p][w] = matchMemoized(p+1,w+1,pattern,word);
		}
		if(p == pattern.length()){
			if(w == word.length()){
				cache[p][w]=1;
				return 1;
			}else{
				cache[p][w]=0;
				return 0;
			}
		}
		
		if(pattern.charAt(p) == '*'){
			if(matchMemoized(p+1,w,pattern,word)==1||(w<word.length()&& matchMemoized(p,w+1,pattern,word)==1)){
				cache[p][w]=1;
				return 1;
			}
		}
		cache[p][w]=0;
		return 0;
	}

	private static void initcache() {
		for(int i=0; i<101; i++){
			for(int j=0; j<101; j++){
				cache[i][j]=-1;
			}
		}
	}

//	private static boolean match(String pattern, String word) {
//		
//		int pos =0;
////		System.out.println("inside func");
////		
////		System.out.println(pos<pattern.length());
////		System.out.println(pos<word.length());
////		System.out.println(pattern.charAt(pos) == word.charAt(pos));
////		System.out.println(pattern.charAt(pos) == '?');
//
//		while(pos<pattern.length() && pos<word.length() && (pattern.charAt(pos) == word.charAt(pos) || pattern.charAt(pos) == '?')){
//				pos++;
//				//System.out.println("pos : " + pos);
//		}
//		if(pos == pattern.length()){
//			if(pos == word.length()){
//				return true;
//			}else{
//				return false;
//			}
//		}
//		if(pattern.charAt(pos) =='*'){
//			//System.out.println("detect *");
//			for(int skip=0; pos+skip<=word.length(); skip++){
//				//System.out.println(pattern.substring(pos+1));
//				//System.out.println(word.substring(pos+skip));
//				if(match(pattern.substring(pos+1),word.substring(pos+skip))){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
}
