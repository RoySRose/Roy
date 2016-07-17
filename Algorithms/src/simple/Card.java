package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Card {

	public static final String[] deck = {"H","D","S","C"};
	public static final String[] stic = {"1","2","3","4","5","6","7","8","9","T"};
	private String card;
	public Card(){
		init();
	}
	public Card(Card cd){
		card = cd.getCard().trim();
	}
	public void init(){
		int a= (int)(Math.random()*deck.length);//0~3
		
		int b= (int)(Math.random()*stic.length);//0~9
		
		card = deck[a]+ stic[b];
	}
	
	public String getCard(){
		return card;
	}
	
	public String toString(){
		return "["+card+"]"; //[H8]
	}
	
	public boolean equals(Object obj){
		boolean isS= false;
		Card cd = (Card) obj;
		if(card.equals(cd.getCard())){
			isS = true;
		}
		return isS;
	}
	public int hasCost(){
		return card.hashCode()+137;
	}
	
	public static void main(String [] args){
		
		
		
		
	}
}
