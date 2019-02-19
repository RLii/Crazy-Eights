import java.util.*;
//The following class extends from the arraylist 
public class Deck extends ArrayList {
	
	Deck()
	{
		super();
	}
	
	
	//methods
	//the following methods shuffles the cards
	void shuffle()
	{
		int n=this.size();
		Random random=new Random();
		for(int i=0;i<this.size();i++)
		{
			int randomValue=i+random.nextInt(n-i);
			card randomElement=(card) this.get(randomValue);//evidence of *****************POLYMORPHISM****************
			this.set(randomValue, this.get(i));
			this.set(i, randomElement);
		}
	}
	//the following method deals the top card
	card deal()
	{
		return (card)this.get(0);
	}
	
	//the following method adds the card to the end of the list
	void addCard(card card)
	{
		this.add(card);
	}
	
}
