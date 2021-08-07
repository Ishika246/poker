

import java.util.Arrays;

import java.util.Scanner;

public class Game 
{

  private final int HAND_SIZE = 3;
  private final int DEALER_SIZE = 5;

	private int again = 1;
	
	// instantiate Deck and Player
	Scanner scan = new Scanner(System.in);
	Deck deck = new Deck();
	Player player = new Player();
	Card[] hand;
	Card[] dealerCards;
	int counter=1;

   
	
	
	
	
	// plays the game
	public void play()
	{
		while (again == 1)
		{
			// fill deck
			deck.fillDeck();
			
			// shuffle
			deck.shuffle();
			//dealer draws
			dealerCards=player.drawDealer(deck);
			// player draws
			hand = player.draw(deck);
			
			// sort hand 		
			Arrays.sort(hand);
			//dealer cards shown 
			this.checkDealer();


			// player redraws
			this.checkHand();
			hand = this.redraw();

			
			// display hand again
			// this.makeHand(); //<--- TA ! un-comment this and change makeHand()
			this.checkHand();
			
			// sort hand		
			Arrays.sort(hand);
			
			this.evaluate();
			
			// play again?
			this.again();
		}
		System.out.println("Thanks for playing! =]");
	}
	
	
	
	// tells Dealer cards on table
		public void checkDealer()
		{ 	 System.out.println("5 Dealer cards on Table ::::::::");

			for (int dealerCounter = 0;dealerCounter < DEALER_SIZE; dealerCounter++)
			{
				this.display(dealerCards[dealerCounter]);
			}
		}
		
	// tells player cards in hand
	public void checkHand()
	
	
	{  
		System.out.println("--Player 1 is playing --");
		System.out.println("Player 1 cards :::::::");
		for (int handCounter = 0; handCounter < HAND_SIZE; handCounter++)
		{
		       this.display(hand[handCounter]);
           //Jocker assumption
			
			
		}
		this.Mode();

	}
	
	//Assuming jocker by 3 modes
	public void Mode() {
		Scanner in=new Scanner(System.in);
		System.out.println("Round============="+counter);
		System.out.println("Enter 1 for assuming highest card as Jocker");
		System.out.println("Enter 2 for assuming lowest card as Jocker");
        System.out.println("Enter 3 for assuming Any card as Jocker");
        
        int num=in.nextInt();
       
        switch(num) {
         
        case 1:
        	
            Arrays.sort(hand);
            Arrays.toString(hand);
            int high = hand[hand.length-1].rank;
            System.out.println("I Assume jocker as highest card   "+high+"");
            this.evaluate();

              break;
        case 2:

            Arrays.sort(hand);
            Arrays.toString(hand);

            int low = hand[0].rank;
            System.out.println("I Assume jocker  as lowest  card     "+low+" ");
            this.evaluate();
        	break;
        case 3:
        	Arrays.sort(hand);
        	Arrays.toString(hand);
        	System.out.println("Assume any jocker card for combination");
        	int number=in.nextInt();
            if(hand[0].rank==number||hand[1].rank==number||hand[2].rank==number) {
            	
                System.out.println("I Assume"+number+"");
            	
            }
            this.evaluate();
            break;
        default:
        	System.out.println("No card as jocker os selected");
        	break;

        }
        counter++;
        
	}
	
	// asks if player wants to redraw
	public Card[] redraw()
	{
		for (int counter = 0; counter < 3; counter++)
		{
			System.out.print("Redraw card " + (counter + 1) + "?" +
					" (1 for yes, 0 for no)");
			int answer = scan.nextInt();
			if (answer == 1)
			{
				hand[counter] = player.redraw(counter, deck);
			}
		}
		deck.refreshDeckPosition();
		return hand;
	}
	
	
	// evaluates the hand
	
	public void evaluate()
	{
	//try {
		 if (this.straightFlush() == 1)
		{
			System.out.println("You have a straight flush!");
		}
		else if (this.flush() == 1)
		{
			System.out.println("You have a flush!");
		}
		else if (this.straight() == 1)
		{
			System.out.println("You have a straight!");
		}
		else if (this.triple() == 1)
		{
			System.out.println("You have a triple!");
			
		}
		
		else if (this.pair() == 1)
		{
			System.out.println("You have a pair!");
		}
		else
		{
			int highCard = this.highCard();
			System.out.println("Your highest card is " + highCard);
		}
	//}
	
	//catch(Exception e) {
	//	System.out.println("Exception  :" +e);
		
	//}
	}
	
	// For same number card:-
	public int sameNumber()
	{

		for (int i=0;i<hand.length-1;i++){
			if (hand[i+1].rank == hand[i].rank) 
				return 1;
		}
		
	return 0;
	}

	
	// checks for a straight flush
	public int straightFlush()
	{
		  if(this.straight()==1 && this.flush()==1)
			  return 1;
		return 0;
		
		
	}
	

	// checks for flush
	public int flush()
	{
		
		for (int i=0;i<hand.length-1;i++){
			if (hand[i+1].suit != hand[i].suit) 
				return 0;
		}
		
	return 1;
	}
	
	// check for straight
	public int straight()
	{
		if(hand[2].rank - hand[0].rank == 2) {
          return 0;}
		return 1;
	}
	
	// checks for triple
	public int triple()
	{
		int card1 = hand[0].rank;
		int card2 = hand[1].rank;
		int card3 = hand[2].rank;
		//int card4 = hand[3].rank;
		//int card5 = hand[4].getValue();
		
		if (card1 == card2 || card2 == card3) {
			return 0;
		}
		return 1;
	}

	
	
	
	// check for pair
	public int pair()
	{
		int card1 = hand[0].rank;
		int card2 = hand[1].rank;
		int card3 = hand[2].rank;
		
		if((card1 == card2 && card3 == card1&& card2!=card3 && card3 != card1) ||
				(card1 != card2 && card2 == card3 && card3 != card1 && card1 == card2)
				){
					return 1;
				}
		return 0;

	}
	
	// find highest card
	public int highCard()
	{
		int highCard = 0;
		for (int counter = 0; counter < 3; counter++)
		{
			if (hand[counter].rank > highCard)
			{
				highCard = hand[counter].rank;
			}
		}
		return highCard;
	}

	
	// checks for a straight flush
		// asks user if they want to play again
	public void again()
	{
		System.out.print("Play again? (1 for yes, 0 for no)");
		again = scan.nextInt();
	}
	
	// generates string for each card in hand
	
	public void display(Card card)
	{
		if (card.rank == 1)
		{
			System.out.print("Ace  ");
			System.out.println();
		}
		if (card.rank == 2)
		{
			System.out.print("Two ");
			System.out.println();
		}
		if (card.rank == 3)
		{
			System.out.print("Three ");
			System.out.println();
		}
		if (card.rank == 4)
		{
			System.out.print("Four ");
		}
		if (card.rank == 5)
		{
			System.out.print("Five ");
			System.out.println();
		}
		if (card.rank == 6)
		{
			System.out.print("Six");
			System.out.println();
		}
		if (card.rank == 7)
		{
			System.out.print("Seven ");
			System.out.println();
		}
		if (card.rank == 8)
		{
			System.out.print("Eight ");
			System.out.println();
		}
		if (card.rank == 9)
		{
			System.out.print("Nine ");
			System.out.println();
		}
		if (card.rank == 10)
		{
			System.out.print("Ten ");
			System.out.println();
		}
		if (card.rank == 11)
		{
			System.out.print("Jack ");
			System.out.println();
		}
		if (card.rank == 12)
		{
			System.out.print("Queen ");
			System.out.println();
		}
		if (card.rank == 13)
		{
			System.out.print("King ");
			System.out.println();
		}
		System.out.println();
		
	}
}
