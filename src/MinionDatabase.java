
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
		if(card.name == "Core Hound")
		{
			return new Minion(9, 5, card.name);
		}
		if(card.name == "Murloc Raider")
		{
			return new Minion(2, 1, card.name);
		}
		if(card.name == "Murloc Tidehunter")
		{
			return new Minion(2, 1, card.name);
		}
		if(card.name == "Murloc Scout")
		{
			return new Minion(1, 1, card.name);
		}
		if(card.name == "Abusive Sergeant")
		{
			return new Minion(2, 1, card.name);
		}
		if(card.name == "Aldor Peacekeeper")
		{
			return new Minion(3, 3, card.name);
		}
		if(card.name == "Amani Berserker")
		{
			return new Minion(2, 3, card.name);
		}
		if(card.name == "Bloodfen Raptor")
		{
			return new Minion(3, 2, card.name);
		}
		
		

		return null;
	}
}
