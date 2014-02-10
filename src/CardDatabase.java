
public class CardDatabase {
	
	public CardDatabase()
	{
		
	}
	
	public Card cardFromName(String s)
	{
		//first lets do the minions!
		if(s.equalsIgnoreCase("Squirrel"))
		{
			return new Card(s,1,true);
		}
		if(s.equalsIgnoreCase("Devilsaur"))
		{
			return new Card(s,5,true);
		}
		if(s.equalsIgnoreCase("Dark Iron Dwarf"))
		{
			return new Card(s,4,true);
		}
		if(s.equalsIgnoreCase("Sen'jin Shieldmasta"))
		{
			return new Card(s,4,true);
		}
		if(s.equalsIgnoreCase("Core Hound"))
		{
			return new Card(s,7,true);
		}
		if(s.equalsIgnoreCase("Murloc Raider"))
		{
			return new Card(s,1,true);
		}
		if(s.equalsIgnoreCase("Murloc Tidehunter"))
		{
			return new Card(s,2,true);
		}
		if(s.equalsIgnoreCase("Murloc Scout"))
		{
			return new Card(s,0,true);
		}
		if(s.equalsIgnoreCase("Abusive Sergeant"))
		{
			return new Card(s,1,true);
		}
		if(s.equalsIgnoreCase("Aldor Peacekeeper"))
		{
			return new Card(s,3,true);
		}
		if(s.equalsIgnoreCase("Amani Berserker"))
		{
			return new Card(s,2,true);
		}
		if(s.equalsIgnoreCase("Bloodfen Raptor"))
		{
			return new Card(s,2,true);
		}
		
		
		//now for shpells
		if(s.equalsIgnoreCase("Fireball"))
		{
			return new Card(s,4,false);
		}
		if(s.equalsIgnoreCase("Holy Smite"))
		{
			return new Card(s,1,false);
		}
		
		return null;
	}

}
