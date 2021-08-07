

public class Player 
{
  
	// gets 5 cards from deck
	
	public Card[] drawDealer(Deck deck)
	{
		Card[] dealerCards = deck.dealerCard();
		return dealerCards;
	}
	//get 3 card for player
	public Card[] draw(Deck deck)
	{
		Card[] hand = deck.deal();
		return hand;
	}
	
	// switches card for a new card
	public Card redraw(int counter, Deck deck)
	{
		Card card = deck.redeal();
		return card;
	}

}
