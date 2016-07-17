package DynaminProgramming;

import java.util.Scanner;

public class WildCardBT {

	public static void main(String [] args){
		
		//ArrayList<String> input = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);

		int T=scan.nextInt();
		
		for(int k=0; k<T; k++){
			
			System.out.println("Case : " + k);
			String pattern =scan.next();
			int wordcnt = scan.nextInt();
			//System.out.println(wordcnt);
			String[] words =  new String[wordcnt];
			
			for(int m=0; m<wordcnt ; m++){
				//System.out.println(m);
				words[m]=scan.next();	
			}
//			for(int m=0; m<wordcnt ; m++){
//				System.out.println(words[m]);	
//			}
			long start = System.currentTimeMillis();
			
			for(int m=0; m<wordcnt ; m++){
			//	System.out.println("run func");
				if(match(pattern,words[m])){
					System.out.println("@@@pass@@@ " + words[m]);
				}
			}

		    long end = System.currentTimeMillis();
		    System.out.println((end - start)/(double)1000);

			
			
		}
	}

	private static boolean match(String pattern, String word) {
		
		int pos =0;
//		System.out.println("inside func");
//		
//		System.out.println(pos<pattern.length());
//		System.out.println(pos<word.length());
//		System.out.println(pattern.charAt(pos) == word.charAt(pos));
//		System.out.println(pattern.charAt(pos) == '?');
		
		
		
		while(pos<pattern.length() && pos<word.length() && (pattern.charAt(pos) == word.charAt(pos) || pattern.charAt(pos) == '?')){
				pos++;
				//System.out.println("pos : " + pos);
		}
		if(pos == pattern.length()){
			if(pos == word.length()){
				return true;
			}else{
				return false;
			}
		}
		if(pattern.charAt(pos) =='*'){
			//System.out.println("detect *");
			for(int skip=0; pos+skip<=word.length(); skip++){
				//System.out.println(pattern.substring(pos+1));
				//System.out.println(word.substring(pos+skip));
				if(match(pattern.substring(pos+1),word.substring(pos+skip))){
					return true;
				}
			}
		}
		return false;
	}
}
