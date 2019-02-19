
public class player{
	//variables
	private String name=null;
	private Deck cardsinhand=new Deck();
	private int score=0;
	//constructors
	player(String name)
	{
		this.name=name;
		score=0;
	}
	//methods
	//The following method returns the score of the player 
	int score()
	{
		return this.score;
	}
	//The following method adds a point to the player's score
	void addScore()
	{
		this.score+=1;
	}
	//The following method returns the name of the selected player
	String returnName()
	{
		return this.name;
	}
	//The following method returns a boolean determined by the amount of cards in the player's hand
	boolean noCards()
	{
		if(this.cardsinhand.size()==0)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	//The following method orders the player's cards by value 
	void orderCardsInHandByValue()
	{
		card temp=null;
		for(int x=0;x<this.cardsinhand.size();x++)
		{
			for(int y=0;y<this.cardsinhand.size()-1;y++)
			{
				if(((card)cardsinhand.get(y)).getValue()>((card)cardsinhand.get(y+1)).getValue())
				{
					
					temp=(card) cardsinhand.get(y+1);
					cardsinhand.set(y+1, (card)cardsinhand.get(y));
					cardsinhand.set(y, temp);
				}
			}
		}
	}
	//The following method orders the player's cards by suit 
	void orderCardsInHandBysuit()
	{
		card temp=null;
		for(int x=0;x<this.cardsinhand.size();x++)
		{
			for(int y=0;y<this.cardsinhand.size()-1;y++)
			{
				if(((card)cardsinhand.get(y)).getSuit().compareTo(((card)cardsinhand.get(y+1)).getSuit())>0)
				{
					temp=(card) cardsinhand.get(y+1);
					cardsinhand.set(y+1, (card)cardsinhand.get(y));
					cardsinhand.set(y, temp);
				}
			}
		}
	}
	//the following method returns the card in the player's hand. However, there are two parameters. The program must input a value and string of the card.
	//Additionally, the function will return an invalid card if the card is not in the hand. 
	card dumpCardInHand(int value, String suit)
	{
		if(hasCard(value, suit)==true)
		{
			for(int x=0;x<cardsinhand.size();x++)
			{
				if(((card)cardsinhand.get(x)).getValue()==value && ((card)cardsinhand.get(x)).getSuit().trim().equals(suit))
				{
					return (card)cardsinhand.get(x);
				}
			}
				return new card(-1,"invalid");
		}
		else
		{
			return new card(-1,"invalid");
		}
		
	}
	//the following function removes a card from the player's hand 
	void remove(int value, String suit)
	{
		
		if(hasCard(value, suit)==true)
		{
			for(int x=0;x<cardsinhand.size();x++)
			{
				if(((card)cardsinhand.get(x)).getValue()==value && ((card)cardsinhand.get(x)).getSuit().trim().equals(suit))
				{
					this.cardsinhand.remove(x);
				}
				
				
			}
		}
		
	}
	//The following function returns a boolean depending if the given card can be found in the player's hand 
	boolean hasCard(int value, String suit)
	{
		boolean valueCheck=false;
		boolean suitCheck=false;
		for(int x=0;x<cardsinhand.size();x++)
		{
			if(((card)cardsinhand.get(x)).getValue()==value)
			{
				valueCheck=true;
				if(((card)cardsinhand.get(x)).getSuit().trim().equals(suit))
				{
					suitCheck=true;
				}
			}
			
		}
		if(valueCheck==true && suitCheck==true)
		{
			return true;
		}
		else{
			return false;
		}
	}
	//the following function returns a boolean depending if the given value can be found in the player's hand 
	boolean hasCardValue(int value)
	{
		boolean valueCheck=false;
		
		for(int x=0;x<cardsinhand.size();x++)
		{
			if(((card)cardsinhand.get(x)).getValue()==value)
			{
				valueCheck=true;
				
			}
			
		}
		if(valueCheck==true)
		{
			return true;
		}
		else{
			return false;
		}
	}
	//the following function returns a boolean depending if the given card can be found in the player's hand 
	boolean matchingCard(card Card)
	{
		boolean check = false;
		for(int x=0;x<cardsinhand.size();x++)
		{
			
			if(((card)cardsinhand.get(x)).getValue()==Card.getValue())
			{
				check=true;
				
			}
			if(((card)cardsinhand.get(x)).getSuit().trim().equals(Card.getSuit().trim()))
			{
				check=true;
			}
			
		}
		if(check==true)
		{
			return true;
		}
		else {
			return false;
		}
		
	}
	//The following function is used to display the player's hand. 
	void showHand()
	{
		for(int x=0;x<cardsinhand.size();x++)
		{
			System.out.println(cardsinhand.get(x).toString());
		}
	}
	//The following function adds a card to the player's hand and removes a card from the deck. 
	void addCard(Deck deck)
	{
		cardsinhand.add(deck.get(0));
		deck.remove(0);
	}
}
