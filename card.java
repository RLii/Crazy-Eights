import java.util.*;
//Ethan Hood, Ernest Chiu, Richard Lii

//May 17th 2018
//Card Class
//This is the class that contains the class used to create the card objects.
public class card {
	//instance variables
	private int value;//the actual numerical value of the card (to make things simple jack is 11, queen 12 and king is 13
	private String suit;//the name of the suit on the card (heart, spade, diamond, clover)
	private String[][] face=new String[10][10];//the actual appearance of the card (built using characters!!)
	private String back;//the back face of the card built using characters
	//constructors
	//the following constructor is used to create a card. The two parameters are a value and a string 
	public card(int value,String suit)
	{
		this.value=value;
		this.suit=suit;
	}
	//methods
	//the following method returns the value of the card
	int getValue(){
		return this.value;
	}
	//the following method returns the suit of the card
	String getSuit(){
		return this.suit;
	}
	//the following returns the suit and value of the card in the form of a string
	public String toString(){
		return (this.suit+" "+this.value);
	}
	
}
