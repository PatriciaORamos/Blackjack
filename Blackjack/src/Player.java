
/**
 * 
 * @author Patricia
 *
 */

//● You need to create Objects for your deck of cards, and your players
public class Player {
	
	private String name;
	private double betValue;
	private int total;

	public String getName() { return name; }
	public void setName(String name) { this.name = name;}
	public double getBetValue() { return betValue; }
	public void setBetValue(double betValue) { this.betValue = betValue; }

	DeckCard deck = new DeckCard();
	
	public Player(String name, double betValue) {
		this.name = name; this.betValue = betValue; total = 0;
	}
	
	/**
	 * method get card and add value in total, because everytime that get a card, the value of the card is add in total of the player
	 */
	public Cards getCard() {
		deck.getNextCard();
		return deck.card;
	}
	
	public void setTotal(int value) {
		total = total + value;
	}
	
	/*
	 * method get value total 
	 */
	public int getTotal() {
		return total;
	}
	
}
