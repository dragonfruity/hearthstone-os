
public class Variables {
	//this class just holds all the variables.
	int playerhealth = 30;
	int enemyhealth = 30;
	Minion[] playerminions = new Minion[8];
	Minion[] enemyminions = new Minion[8];
	Hand hand = new Hand();
	boolean isFirst;
	int playermaxmana = 0;
	int playermana = 0;
	int enemymaxmana = 0;
	int enemymana = 0;
	public Deck realtinkdeck;
	String info = "";
	
	PlayerData pd = new PlayerData();
	
	public Variables()
	{
		realtinkdeck = pd.deckAllMinions;
	}

}
