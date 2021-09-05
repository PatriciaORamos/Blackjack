import java.util.Random;

/**
 * 
 * @author 
 *
 */

//● You need to create Objects for your deck of cards, and your players
public class DeckCard {

	private int number;
	private String alreadyCards = "";
	private Random randowCard = new Random();
	
	Cards card = new Cards();
	
	/*
	 * method create suit of the card
	 */
	public String createSuits() {
		String typeSuite = "";		
		switch (number % 4) {
			case 0: typeSuite = "CUBS"; break;
			case 1: typeSuite = "HEARTS"; break;
			case 2: typeSuite = "SPADES"; break;
			case 3: typeSuite = "DIAMONDS"; break;		
		}		
		return typeSuite;
	}
	
	/*
	 * method create description of value of the card 
	 */
	public String createValueCardName() {
		String typeValue = "";		
		switch (number % 13) {
			case 0: typeValue = "ACE"; break;
			case 1: typeValue = "TWO"; break;
			case 2: typeValue = "THREE"; break;
			case 3: typeValue = "FOUR"; break;
			case 4: typeValue = "FIVE"; break;
			case 5: typeValue = "SIX"; break;
			case 6: typeValue = "SEVE"; break;
			case 7: typeValue = "EIGHT"; break;
			case 8: typeValue = "NINE"; break;
			case 9: typeValue = "TEN"; break;
			case 10: typeValue = "JACK"; break;
			case 11: typeValue = "QUEEN"; break;
			case 12: typeValue = "KING"; break;
		}		
		return typeValue;
	}
		
	/*
	 * method create a sort card, 
	 */
	public void getNextCard() {		
		number = randowCard.nextInt(52);
		while(alreadyCards.indexOf(number) != -1) {
			number = randowCard.nextInt(52);
		}
		alreadyCards = alreadyCards + number;
		
		String suit = createSuits();
		int valueCard = (number % 13) + 1;		
		
		
		//Numbered cards are all the same value as they show (a 2 is worth 2, a 3 is worth 3, and so on). All face cards (jack, queen, king) are worth 10,
		if(valueCard == 11 || valueCard == 12 || valueCard == 13) {
			valueCard = 10;
		}
		
		card.setSuit(suit);
		card.setValueCard(valueCard);
		card.setValueCardName(createValueCardName() + " of " + suit);
	}
	
	
	
	

}
