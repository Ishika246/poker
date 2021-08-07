

import java.util.*;

public class Deck 
{
  
	private final int DECK_SIZE = 52;
	private final int SHUFFLE_EXCHANGES = 200;
	private final int HAND_SIZE = 3;
	
	public int restOfDeck = 6;
	Card[] dealerCard=new Card[5];
	Card[] deck = new Card[DECK_SIZE];
	Random r = new Random();
	
	
	// fill deck with cards
	public void fillDeck()
	{
		int counter = 0;
		for (int suit = 1; suit <= 4; suit++)
		{
			for (int rank = 1; rank <= 13; rank++)
			{
				deck[counter] = new Card();
				deck[counter].suit = suit;
				deck[counter].rank = rank;
				counter++;
			}
		}
	}
	
	// shuffle deck
	public void shuffle()
	{
		for (int x = 0; x <= SHUFFLE_EXCHANGES; x++)
		{
			int number1 = r.nextInt(DECK_SIZE);
			int number2 = r.nextInt(DECK_SIZE);
			Card temp = deck[number1];
			deck[number1] = deck[number2];
			deck[number2] = temp;
		}
	}
	
	// deals 5 cards
	public Card[] dealerCard() {
		Card[] dealerCard=new Card[5];

		for (int deckPosition = 0; deckPosition < dealerCard.length; deckPosition++)
		{
			dealerCard[deckPosition] = deck[deckPosition];
		}
		return dealerCard;
		
	}
	
	public Card[] deal()
	{
		Card[] hand = new Card[HAND_SIZE];
		for (int deckPosition = 0; deckPosition < hand.length; deckPosition++)
		{
			hand[deckPosition] = deck[deckPosition];
		}
		return hand;
	}
	
	// deals cards for redraw
	public Card redeal()
	{
		Card nextCard = deck[restOfDeck];
		restOfDeck++;
		return nextCard;
	}
	
	// refreshes deck position to 6 for next hand
	public void refreshDeckPosition()
	{
		restOfDeck = 6;
	}

}
