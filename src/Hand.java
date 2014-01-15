import java.util.Stack;


public class Hand {

	Stack<Card> hand = new Stack<Card>();
	
	public Hand()
	{
		
	}
	
	public void draw(Card card)
	{
		hand.add(card);
	}
	
	public void remove(int position)
	{
		hand.remove(position);
	}
}
