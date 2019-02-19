//The following class extends the deck class(array list)
public class pile extends Deck{
	//variables
	private card topCard=null;
	
	//constructors
	public pile(Deck deck)
	{
		super();
		this.add(deck.get(0));
		deck.remove(0);
	}
	card getTopCard()
	{
		topCard=(card) this.get(0);
		return topCard;
		
	}
}
