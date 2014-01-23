
public class MinionDatabase {
	public MinionDatabase()
	{
		
	}
	
	public Minion minionFromCard(Card card)
	{
		if(card.name == "Squirrel")
		{
			return new Minion(1, 1, card.name);
		}
		if(card.name == "Devilsaur")
		{
			return new Minion(5, 5, card.name);
		}
		if(card.name == "Dark Iron Dwarf")
		{
			return new Minion(4, 4, card.name);
		}
		if(card.name == "Sen'jin Shieldmasta")
		{
			return new Minion(3, 5, card.name);
		}

		return null;
	}
}
