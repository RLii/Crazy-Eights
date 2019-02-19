import java.util.*;
//Ethan Hood, Ernest Chiu, Richard Lii
//May 17th 2018
//Game Class 
//This is the main class that the game of crazy eights will run off of.
public class game {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("Welcome to Crazy Eights, this game was created by Ernest, Richard and Ethan.");
		//****************creates the deck and shuffles it*************
		System.out.println("Creating Decks");
		Deck deck=new Deck();
		for (int x=1;x<14;x++){
			deck.add(new card( x," diamonds"));
			deck.add(new card( x," clubs"));
			deck.add(new card( x," hearts"));
			deck.add(new card( x," spades"));
		}
		System.out.println("Shuffling Decks");
		deck.shuffle();	
		//******************creates the players and assigns them their cards****************
		System.out.println("Please enter player 1's name: ");
		player player1=new player(scan.next());
		System.out.println("Please enter player 2's name: ");
		player player2=new player(scan.next());
		System.out.println("Dealing Cards");
		for(int x=0;x<8;x++)
		{
			player1.addCard(deck);
			player2.addCard(deck);
		}
		//******************creates the pile of cards******************************
		System.out.println("Creating Pile");
		pile pile=new pile(deck);
		//System.out.println(pile.getTopCard().toString());
		/*for(int x=0;x<pile.size();x++){
			System.out.print(((card) pile.get(x)).getValue());
			System.out.println(((card) pile.get(x)).getSuit());
		}
		System.out.println(pile.size());*/
		
		//****************player 1 options**********************
		int game=0;//variable used to switch between player 1 and player 2's turn 
		int menu=0;//variable is used to switch between options on a player's turn 
		while (game!=3)//while the game variable does not equal to 3
		{
			if(game==0)
			{
				menu=0;
				while (1==1)
				{
					//*****************************************************Player Menu**********************************************
					System.out.println("-------------------------------------------------");
					System.out.println(player1.returnName()+" Turn:");
					System.out.println("The top card of the pile is" +pile.getTopCard().toString());
					System.out.println("Your current hand is ");
					player1.showHand();
					if(player1.matchingCard(pile.getTopCard())==false && player1.hasCardValue(8)==false)
					{
						System.out.println("You have no cards that can played");
						System.out.println("You will now draw a card");
						System.out.println("The card: "+deck.get(0).toString()+" has been added to your hand");
						player1.addCard(deck);
						game=1;
						break;
					}
					System.out.println("Order cards in hand by value....................press 1");
					System.out.println("Order cards in hand by suit.....................press 2");
					System.out.println("dump card.......................................press 3");
					System.out.println("show hand.......................................press 4");
					System.out.println("Quit game.......................................press 5");
					
					menu=scan.nextInt();
					//If the player selects 1, the cards in his hand are ordered by value 
					if(menu==1)
					{
						player1.orderCardsInHandByValue();
					}
					//If the player selects 2, the cards in his hand are ordered by suit
					else if(menu==2)
					{
						player1.orderCardsInHandBysuit();
					}
					//*******************************************************************Dump Card******************************************
					//The following is the code used to drop a card onto the pile 
					else if(menu==3)
					{
						//first local variables are declared
						boolean invalidCard=false; 
						boolean skipTurn=false;
						String suit=null;
						int value=0;
						//Then the player is asked which card they would like to dump
						System.out.println("Please enter the suit of the card you would like to dump eg.('diamonds','hearts','spades','clubs' )");
						suit=scan.next();
						System.out.println("Please enter the value of the card you would like to dump eg.('10','3','5')");
						value=scan.nextInt();
						suit=suit.trim();
						//after that, the selected card is used to see if it matches the suit or number on the pile
						//furthermore, if the card has a value of 8 the number, it can be placed in any situation
						if(pile.getTopCard().getSuit().equals(player1.dumpCardInHand(value, suit).getSuit())||pile.getTopCard().getValue()==player1.dumpCardInHand(value, suit).getValue()||player1.dumpCardInHand(value, suit).getValue()==8)
						{
							//this adds the card to the pile
							pile.add(0, player1.dumpCardInHand(value, suit));
							//the following if statement checks if the card's value is 2. If so, it will force the opponent to pick up two cards.
							if(player1.dumpCardInHand(value, suit).getValue()==2)
							{
								System.out.println(player2.returnName()+" Pick up two cards");
								System.out.println("The cards: "+deck.get(0).toString()+" and "+deck.get(1).toString()+ " has been added to your hand");
								player2.addCard(deck);
								player2.addCard(deck);
							}
							//The following if statement checks if the card's value is 8. If so, the player has the ability to change the suit of the top card. 
							if(player1.dumpCardInHand(value, suit).getValue()==8)
							{
								System.out.println("You placed down an 8!");
								System.out.println("What suit would you like to change to?eg.('diamonds','hearts','spades','clubs' )");
								String change=scan.next();
								pile.add(0, new card(8, " "+change));
							}
							//The following if statement checks if the card's value is 11. If so, the opponent's turn is skipped
							if(player1.dumpCardInHand(value, suit).getValue()==11)
							{
								System.out.println(player2.returnName()+"'s turn was skipped!");
								skipTurn=true;
							}
							//the following statement checks if the card's suit and value is a spades 12. If so, the opponent is forced to draw 5 cards.
							if(player1.dumpCardInHand(value, suit).getSuit()==" spades" && player1.dumpCardInHand(value, suit).getValue()==12)
							{
								System.out.println(player2.returnName()+" Pick up 5 cards");
								System.out.println("5 cards has been added to your hand");
								for(int x=0;x<5;x++)
								{
									player2.addCard(deck);
								}
							}
							//After all that, the player's card is finally removed from his hand.
							player1.remove(value, suit);
							System.out.println("The top card of the pile is now: "+pile.getTopCard().toString());
							
						}
						//the following else statement occurs when the player declared a card that does not have a matching suit or value. 
						//Additionally, the else statement will also occur when the player declares a card they do not posess
						else 
						{
							invalidCard=true;
							System.out.println("You entered a card that does not match the number or the suit of the top card");
						}
						//The following if statement checks if the player's hand is empty. If so, it will display a message to notify the winner.
						if(player1.noCards()==true)
						{
							game=2;
							player1.addScore();
							System.out.println(player1.returnName()+" Wins!");
							System.out.println(player1.returnName()+" Now has "+player1.score()+" points!");
							System.out.println("Play Again.......................press 0");
							System.out.println("Quit.............................press 3");
							game=scan.nextInt();
							break;
						}
						//The following if statements are used to declare if the opponent either placed down an 11 or placed an invalid card.
						if(skipTurn==true)
						{
							game=0;
							break;
						}
						if(invalidCard==false)
						{
							game=1;
							break;
						}
						
					}
					//The following menu shows the player's hand.
					else if(menu==4)
					{
						player1.showHand();
					}
					else if(menu==5)
					{
						
						game=1;
						break;
						
					}
				}
			}
			//The following code is a duplicate of the code above. However, all the values are replaced with player two's cards and options. 
			else if(game==1)
			{
				menu=0;
				while (1==1)
				{
					//*****************************************************Player Menu**********************************************
					System.out.println("-------------------------------------------------");
					System.out.println(player2.returnName()+" Turn:");
					System.out.println("The top card of the pile is" +pile.getTopCard().toString());
					System.out.println("Your current hand is ");
					player2.showHand();
					if(player2.matchingCard(pile.getTopCard())==false && player2.hasCardValue(8)==false)
					{
						System.out.println("You have no cards that can played");
						System.out.println("You will now draw a card");
						System.out.println("The card: "+deck.get(0).toString()+" has been added to your hand");
						player2.addCard(deck);
						game=0;
						break;
					}
					System.out.println("Order cards in hand by value....................press 1");
					System.out.println("Order cards in hand by suit.....................press 2");
					System.out.println("dump card.......................................press 3");
					System.out.println("show hand.......................................press 4");
					System.out.println("Quit game.......................................press 5");
					
					menu=scan.nextInt();
					if(menu==1)
					{
						player2.orderCardsInHandByValue();
					}
					else if(menu==2)
					{
						player2.orderCardsInHandBysuit();
					}
					else if(menu==3)
					{
						boolean skipTurn=false;
						boolean invalidCard=false;
						String suit=null;
						int value=0;
						System.out.println("Please enter the suit of the card you would like to dump eg.('diamonds','hearts','spades','clubs' )");
						suit=scan.next();
						System.out.println("Please enter the value of the card you would like to dump eg.('10','3','5')");
						value=scan.nextInt();
						suit=suit.trim();
						if(pile.getTopCard().getSuit().equals(player2.dumpCardInHand(value, suit).getSuit())||pile.getTopCard().getValue()==player2.dumpCardInHand(value, suit).getValue()||player2.dumpCardInHand(value, suit).getValue()==8)
						{
							pile.add(0, player2.dumpCardInHand(value, suit));
							if(player2.dumpCardInHand(value, suit).getValue()==2)
							{
								System.out.println(player1.returnName()+" Pick up two cards");
								System.out.println("The cards: "+deck.get(0).toString()+" and "+deck.get(1).toString()+ " has been added to your hand");
								player1.addCard(deck);
								player1.addCard(deck);
							}
							if(player2.dumpCardInHand(value, suit).getValue()==8)
							{
								System.out.println("You placed down an 8!");
								System.out.println("What suit would you like to change to?eg.('diamonds','hearts','spades','clubs' )");
								String change=scan.next();
								pile.add(0, new card(8, " "+change));
							}
							if(player2.dumpCardInHand(value, suit).getValue()==11)
							{
								System.out.println(player1.returnName()+"'s turn was skipped!");
								skipTurn=true;
							}
							if(player2.dumpCardInHand(value, suit).getSuit()==" spades" && player2.dumpCardInHand(value, suit).getValue()==12)
							{
								System.out.println(player1.returnName()+" Pick up 5 cards");
								System.out.println("5 cards has been added to your hand");
								for(int x=0;x<5;x++)
								{
									player1.addCard(deck);
								}
							}
							player2.remove(value, suit);
							System.out.println("The top card of the pile is now: "+pile.getTopCard().toString());
						}
						else 
						{
							System.out.println("You entered a card that does not match the number or the suit of the top card");
							invalidCard=true;
						}
						if(player2.noCards()==true)
						{
							game=2;
							player2.addScore();
							System.out.println(player2.returnName()+" Wins!");
							System.out.println(player2.returnName()+" Now has "+player2.score()+" points!");
							System.out.println("Play Again.......................press 0");
							System.out.println("Quit.............................press 3");
							game=scan.nextInt();
							break;
						}
						if(skipTurn==true)
						{
							game=1;
							break;
						}
						if(invalidCard==false)
						{
							game=0;
							break;
						}
						
					}
					//The following menu shows the player's hand.
					else if(menu==4)
					{
						player2.showHand();
					}
				
				}
			}
			
		
			
			
			
			
			
			
		}
		
		
		
	}

}
