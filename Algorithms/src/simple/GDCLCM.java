package simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GDCLCM {

	public static int GCD(int a, int b){
		
		if(b==0){
			return a;
		}else{
			return GCD(b,a%b);
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		int a = 120;
		int b = 65;
		
		int c = GCD(a,b);
//		System.out.println(c);
//		System.out.println(a*b/c);
		Boolean h;
	}
}
