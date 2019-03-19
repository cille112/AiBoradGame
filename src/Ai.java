import java.util.ArrayList;

public class Ai extends Player{

	public Ai(Card[] cards){
		super(cards);
	}
	
	//max max - start regler
	

	public void takeAction(GameBoard gb){
		int[] play = new int[4];
		int[] discard = new int[4];
		for (int i = 0; i < hand.length; i++) {
			ArrayList<Card> belief = beliefStates(i);
			play[i] = simulatePLay(gb, belief);
			discard[i] = simulateDiscard(gb, belief);
		}
		
		int maxHint = evalf(gb)-1; // skal rettes til bedre evalf
		
		this.playCard(gb, 2);
	}
	
	public int simulatePLay(GameBoard gb, ArrayList<Card> card){
		int total = 0;
		for (int i = 0; i < card.size(); i++) {
			GameBoard clone;
			clone = gb.getClone();
			clone.putCardOnTable(card.get(i));
			total =+ evalf(clone);

		}
		
		return total/card.size();
	}
	
	public int simulateDiscard(GameBoard gb, ArrayList<Card> card){
		int total = 0;
		for (int i = 0; i < card.size(); i++) {
			GameBoard clone;
			clone = gb.getClone();
			clone.discardCard(card.get(i));
			total =+ evalf(clone);

		}
		return total/card.size();
	}
	
	public ArrayList<Card> beliefStates (int cardNumber){
		ArrayList<Card> belief = new ArrayList<>();
		belief.add(hand[cardNumber]); // skal finde rigtige belief states
		return belief;
	}
	
	public int evalf(GameBoard gb){
		return (gb.getPoints() * 2) + gb.getLife() + gb.getHints();
	}
	
}
