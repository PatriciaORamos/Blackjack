import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 
 *
 */
public class BlackJackManager {

	private static Player player, dealer;

	//● You are using arrays/ArrayLists appropriately to create decks/hands of cards.
	private static ArrayList<String> listCardPLayer;
	private static ArrayList<String> listCardDealer;

	public static void run () {
		Scanner key = new Scanner(System.in);
		listCardPLayer = new ArrayList<>();
		listCardDealer = new ArrayList<>();
						
		System.out.println("Welcome to Blackjack. Lets go to play? (Y/N)");
		String isPlay = key.next();

		if(isPlay.equalsIgnoreCase("Y")) {
			System.out.println("What is your name?");
			String name = key.next();
			System.out.println("What is value of your bet");
			double valueBet = Double.parseDouble(key.next());
			
			//create 
			player = new Player(name, valueBet);
			dealer = new Player("Dealer", valueBet);			
			System.out.println("\nHi " + name +", let is play");
			
			//Both the player and the dealer are given two cards. 
			//You are allowed to see both of your cards.
			System.out.println("\n" + name + ", in your hand is a:");
			for (int i = 0; i < 2; i++) {
				Cards card = player.getCard();
				for(String nameCard: listCardPLayer) {
					if(nameCard.equalsIgnoreCase(card.getValueCardName())){
						card = player.getCard();
					}
				}				
				
				listCardPLayer.add(card.getValueCardName());
				System.out.println(" -- " + listCardPLayer.get(i));
				int value = card.getValueCard();
				playerCardACE(key, card, value);
			}
			
			
			//You are able to see one of the dealer’s cards, but not the other.
			System.out.println("In Dealer hand is a:");
			for (int i = 0; i < 2; i++) {
				Cards card = dealer.getCard();
				for(String nameCard: listCardDealer) {
					if(nameCard.equalsIgnoreCase(card.getValueCardName())){
						card = dealer.getCard();
					}
				}	
				listCardDealer.add(card.getValueCardName());
				if(i != 1) {
					System.out.println(" -- " + listCardDealer.get(i));
				} else {
					System.out.println(" -- PRIVATE CARD-- ");
				}
				dealerCardACE(card);
			}
			
			validateTotalLess21(key);			
			
		}		
		System.out.println("\n ------ END ------");
		
		//● You need to allow the user to play again.
		run();
	
	}


	/**
	 * method ask player to choice value of the Ace card
	 * @param key
	 * @param card
	 * @param value
	 */
	private static void playerCardACE(Scanner key, Cards card, int value) {
		//and an ace can be worth either 11 or 1 (player’s choice).
		if(card.getValueCard() == 1) {
			System.out.println("Your card is ACE, choice value for card. (1)  (11)");
			value = key.nextInt();
		}
		player.setTotal(value);
	}

	/**
	 * method make decision of the value of the Ace card to dealer
	 * @param card 
	 */
	private static void dealerCardACE(Cards card) {
		int value = card.getValueCard();
		if(value == 1) { 
			value = 11;
		}		
		if(value == 1 && (player.getTotal() + value) > 21) {
			value = 1;
		}	
		dealer.setTotal(value);
	}

	/**
	 * method valid if player or dealer have total less 21. In case positive, player make decision, else, system valid resulted
	 * @param key
	 */
	private static void validateTotalLess21(Scanner key) {
		//system valid if player or dealer bust, if no, system ask player if stand or hit.
		if(player.getTotal() < 21 && dealer.getTotal() < 21) {
			
			//decide if you want to risk taking another card, or staying put with the cards you have.
			decision(key);
		} else {
			validateResultTotal();
		}
	}
	
	/**
	 * method ask player if stand(keep) or hit (more card)
	 * @param key
	 */
	private static void decision(Scanner key) {
		/** ● You need to appropriately guide the user through a hand of Blackjack, allowing
		them to choose to take another card or keep the cards they have, ending their 
		turn as necessary if they go over 21.*/
		
		System.out.println("What do you want? (Stand)  (Hit)");
		String decision = key.next();
		
		
		if(decision.equalsIgnoreCase("stand")){
			stand();
		} else if (decision.equalsIgnoreCase("hit")) {
			hit(key);
		} else 	{
			System.out.println("What do you want? (Stand)  (Hit)");
			decision = key.next();
		}
	}

	
	
	/**
	 * method give card to player and ask player if stand or hit
	 * @param key
	 */
	public static void hit(Scanner key) {
		Cards card = player.getCard();		
		for(String nameCard: listCardPLayer) {
			if(nameCard.equalsIgnoreCase(card.getValueCardName())){
				card = player.getCard();
			}
		}
		listCardPLayer.add(card.getValueCardName());		
		System.out.println(player.getName() + ", In your hand is a:");
		int value = card.getValueCard();		
		
		for (int i = 0; i < listCardPLayer.size(); i++) {
			System.out.println(" -- " + listCardPLayer.get(i));
		}	
		playerCardACE(key, card, value);
		validateTotalLess21(key);		
	}
	

	/**
	 * method valid if total Dealer's card is less or equal 16 system add card to dealer and display. After compare total of the player and dealer.
	 */
	public static void stand() {
		//● You need to appropriately move the dealer through a hand, following the rules laid out.
		//The dealer then does the same. If the dealer has 16 or more, they MUST keep that 	number. If they have less, they MUST take another card.
		while (dealer.getTotal() <= 16) {
			
			Cards card = dealer.getCard();
			
			//validate if this card was delivery to dealer, in case positive, system get other card
			for(String nameCard: listCardDealer) {
				if(nameCard.equalsIgnoreCase(card.getValueCardName())){
					card = dealer.getCard();
				}
			}
			
			listCardDealer.add(card.getValueCardName());
	
			dealerCardACE(card);
			
			System.out.println("In Dealer hand is a:");
			for (int i = 0; i < listCardDealer.size(); i++) {
				System.out.println(" -- " + listCardDealer.get(i));
			}
		}				
		validateResultTotal();
	}

	/**
	 * method compare total of the player and dealer and define situation of the game.
	 */
	private static void validateResultTotal() {
		System.out.println("YOUR TOTAL: " + player.getTotal());
		System.out.println("DEALER TOTAL: " + dealer.getTotal());
		
		//● You need to compare hand totals and declare a winner.
		
		if(dealer.getTotal() > 21) {
			System.out.println("*** YOU WINNER, DEALER BUST ***");
		} else if (dealer.getTotal() == 21) {
			System.out.println("*** YOU LOSER, DEALER BLACKJACK ***");
		} else 		if(player.getTotal() > 21) {
			System.out.println("*** YOU LOSER, YOU BUST ***");
		} else if (player.getTotal() == 21) {
			System.out.println("*** DEALER LOSER, YOU BLACKJACK ***");
		} else {
			if(dealer.getTotal() == player.getTotal()) {
				System.out.println("*** GAME TIED! ***");
				//If the dealer goes bust, the player wins automatically.
			} else if(dealer.getTotal() >= player.getTotal()) {
				System.out.println("*** YOU LOSER "+ player.getBetValue()+", DEALER HIGHER TOTAL ***");
			} else {
				System.out.println("*** YOU WINNER, YOU HAVE HIGHER TOTAL ***");
			}
		}
	}
}
