import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static TestRedirect t;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// who doesnt love some local variables!
		Variables v = new Variables();
		
		
		//final TextDemo b = new TextDemo();
		
		t = new TestRedirect();
		
		t.print("s");
		
		// scanner for inputs
		Scanner input = new Scanner(System.in);

		// filling the array with invisible minions
		for (int i = 0; i < 6; i++) {
			v.playerminions[i] = new Minion(0, 1, "null");
			v.enemyminions[i] = new Minion(0, 1, "null");
		}

		print("Ho-hoh! Good to see ya! Find a spot around the hearth if you can!");
		print("hey github nice to cya");
		print("Now, lets begin a test game! Ye can borrow one our beginner decks over there.");

		// startGame();

		// test game
		print("This deck is full of only 1/1 squirrels and 5/5 Devilsaurs. It's Tinkmaster's favorite!");

		Stack<Card> tinkdeck = new Stack<Card>();
		for (int i = 0; i < 30; i++) {
			if ((i % 2) == 0) {
				tinkdeck.push(new Card("Devilsaur", 4));
			} else {
				tinkdeck.push(new Card("Squirrel", 1));
			}
		}

		v.realtinkdeck = new Deck(tinkdeck);

		nl();
		// alright, lets draw a starting hand.

		v.isFirst = true;

		startingHand(v.hand, v.realtinkdeck, input, v.isFirst);

		//ok now the actual game loop is starting.
		
		
		
		
		while(true)
		{
		
			//playMinion(v.enemyminions, new Minion(4,5,"Chillwind Yeti"));
			enemyTurn(v);


			playerTurn(v,input);
		
		}



	}

	public static Card drawCard(Deck deck) {
		int temprand = random(deck.deck.size() - 1);
		Card tempcard = deck.deck.get(temprand);
		deck.deck.remove(temprand);
		return tempcard;

	}
	
	public static void playerTurn(Variables v, Scanner input)
	{
		
		//first we do setup such as setting mana values, drawing a card, etc.
		v.playermaxmana++;
		v.playermana = v.playermaxmana;
		
		drawCardToHand(v.hand,v.realtinkdeck);
		
		for (int i = 0; i < 6; i++) {
			if (v.playerminions[i].name != "null") {
				v.playerminions[i].canAttack = true;
			}
			if (v.playerminions[i].name == "null") {
				v.playerminions[i].canAttack = false;
			}
		}
		
		updateBoard(v.playerhealth, v.enemyhealth, v.playerminions, v.enemyminions, v.hand, v);

		
		boolean playerTurn = true;

		while(playerTurn)
		{
			

			//prepareInput();
			String inputs = input.nextLine();

			//play 1
			if(inputs.length() == 6)
			{
				playcardloop:
					if(inputs.substring(0, 4).equalsIgnoreCase("play"))
					{

						String number = inputs.substring(5);

						int cardPlayed;

						try {
							cardPlayed = Integer.parseInt(number);
						} catch (NumberFormatException e) {
							print("That's not a number!");
							break playcardloop;
						}

						if(cardPlayed > v.hand.hand.size())
						{
							print("You don't have that many cards in your hand!");
							break playcardloop;
						}
						else
						{
							if(v.hand.hand.get(cardPlayed -1).manacost <= v.playermana)
							{
								checknull:
									for(int i = 0; i < 6; i++)
									{
										if(v.playerminions[i].name == "null")
										{
											print("played");
											v.playerminions[i] = minionFromCard(v.hand.hand.get(cardPlayed - 1));
											v.playermana = v.playermana - v.hand.hand.get(cardPlayed -1).manacost;

											v.hand.remove(cardPlayed - 1);

											break checknull;
											
										}
									}
							}
							else
							{
								print("I don't have enough mana!");
							}
						}

					}



			}
			//attack 1 with 2
			else if(inputs.length() == 15)
			{
				attackminionloop:
					if(inputs.substring(0, 6).equalsIgnoreCase("attack"))
					{

						String number = inputs.substring(7,8);


						int target;

						try {
							target = Integer.parseInt(number);
						} catch (NumberFormatException e) {
							print("Thats not a number");
							break attackminionloop;
						}

						if(inputs.substring(9,13).equalsIgnoreCase("with"))
						{
							String number2 = inputs.substring(14,15);


							int attacker;

							try {
								attacker = Integer.parseInt(number2);
							} catch (NumberFormatException e) {
								print("Thats not a number");
								break attackminionloop;
							}
							
							//print(Integer.toString(attacker));

							//alright, so everything works with validating the command paramenters. 
							//now we just need to check if there are actual minions there
							//print("command works");

							if(target == 0)
							{
								if(attacker >=1)
								{
									if(v.playerminions[attacker-1].name != "null")
									{
										
										if(v.playerminions[attacker-1].canAttack == true)
										{
											print("attacking TO THE FACE with"  + v.playerminions[attacker-1].name);
											//call an attack function here! congrats it worked
											v.enemyhealth = v.enemyhealth - v.playerminions[attacker-1].damage;
										}
										else
										{
											print("that guy cant attack yet. wait a turn!");
										}
										
										
									}
									else
									{
										print("thats not a valid player minion");
										break attackminionloop;
									}
								}
								else
								{
									print("comon, choose > 1");
									break attackminionloop;
								}
							}
							else if(target >= 1)
							{
								if(v.enemyminions[target-1].name != "null")
								{
									if(attacker >=1)
									{
										if(v.playerminions[attacker-1].name != "null")
										{
											if(v.playerminions[attacker-1].canAttack == true)
											{
												print("attacking " + v.enemyminions[target-1].name + " with " + v.playerminions[attacker-1].name);
												//call an attack function here! congrats it worked
												attackMinion(v.playerminions[attacker-1], v.enemyminions[target-1],v);
											}
											else
											{
												print("that guy cant attack yet. wait a turn!");
											}
										}
										else
										{
											print("thats not a valid player minion");
											break attackminionloop;
										}
									}
									else
									{
										print("comon, choose > 1");
										break attackminionloop;
									}

								}
								else
								{
									print("u wot m8");
									break attackminionloop;
								}
							}

							else
							{
								print("thats not a valid enemy minion!");
								break attackminionloop;
							}



						}
						else
						{
							print("attack _ with _");
						}
					}
			}

			else if(inputs.length() == 7)
			{
				if(inputs.equalsIgnoreCase("endturn"))
				{
					playerTurn = false;

				}

			}
			
			if(playerTurn == true)
			{
				updateBoard(v.playerhealth, v.enemyhealth, v.playerminions, v.enemyminions, v.hand, v);
			}

		}


	}

	public static void insertCard(Deck deck, Card card) {
		deck.deck.add(card);
	}

	public static void updateBoard(int playerhealth, int enemyhealth, Minion[] playerminions, Minion[] enemyminions, Hand hand, Variables v) {
		//print("");
		nl();//clearConsole();//print("Heres the current board!");
		print("Enemy controls:                                                                   Enemy Mana: " + v.enemymana + "/" + v.enemymaxmana);
		print("    0. Enemy has " + v.enemyhealth + "health");
		for (int i = 0; i < 6; i++) {
			if (enemyminions[i].name != "null") {
				print("    " + (i+1) + ". " + enemyminions[i].name + " can do " + enemyminions[i].damage + " damage, and has " + enemyminions[i].currenthealth + "/" + enemyminions[i].maxhealth + " health.");
			}
		}
		nl();

		print("You control:");
		print("    0. You has " + v.playerhealth + "health");
		for (int i = 0; i < 6; i++) {
			if (playerminions[i].name != "null") {
				print("    " + (i+1) + ". " + playerminions[i].name + " can do " + playerminions[i].damage + " damage, and has " + playerminions[i].currenthealth + "/" + playerminions[i].maxhealth + " health.");

			}
		}
		nl();
		print("Your hand has:                                                                       Mana: " + v.playermana + "/" + v.playermaxmana);
		for (int i = 0; i < hand.hand.size(); i++) {
			print(i + 1 + ". " + hand.hand.get(i).name + " (" + hand.hand.get(i).manacost + ")");
		}

	}

	public static int random(int outof) {
		return 1 + (int) (Math.random() * ((outof - 1) + 1));
	}

	public static boolean chance(int chances, int outof) {
		for (int i = 0; i < chances; i++) {
			int random = (int) (Math.random() * outof + 1);
			if (random == 1) {
				return true;
			}
		}

		return false;
	}

	public static void prepareInput()
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void print(String s) {
		System.out.println(s);
		t.print(s);
	}

	private static void print(int s) {
		System.out.println(s);
		
	}

	public static void nl() {
		print("");
	}

	public static void startGame() {

	}

	public static void startTest() {

	}

	public static void playMinion(Minion[] board, Minion minion) {

		for (int i = 0; i < 6; i++) {
			if (board[i].name == "null") {
				board[i] = minion;

				// escape loop
				i = 6;
			}

		}

	}

	
	public static void drawCardToHand(Hand hand, Deck deck)
	{
		Card tempcard = drawCard(deck);
		hand.draw(tempcard);
	}


	public static Hand startingHand(Hand hand, Deck deck, Scanner input, boolean isFirst)
	{
		for (int i = 0; i < 3; i++) {
			Card tempcard = drawCard(deck);
			hand.draw(tempcard);

		}

		while (true) {
			print("Here's your starting hand! You go first.");
			for (int i = 0; i < 3; i++) {
				print(i + 1 + ". " + hand.hand.get(i).name + " (" + hand.hand.get(i).manacost + ")");
			}

			String inputs = null;
			print("Type ready once you're ready to go! Or, type redraw and a number to mulligan the card.");
			//prepareInput();
			inputs = input.nextLine();

			if (inputs.equalsIgnoreCase("ready")) {
				return hand;
			}
			if (inputs.length() > 7) {
				drawloop: 
					if (inputs.substring(0, 6).equalsIgnoreCase("redraw")) {
						String number = inputs.substring(7);

						int cardMulliganned;

						try {
							cardMulliganned = Integer.parseInt(number);
						} catch (NumberFormatException e) {
							print("That's not a number!");
							break drawloop;
						}

						if (isFirst == true) {
							if (cardMulliganned < 1) {
								print("Choose 1, 2, or 3!");
								break drawloop;
							}
							if (cardMulliganned > 3) {
								print("Choose 1, 2, or 3!");
								break drawloop;
							}

						} else {
							if (cardMulliganned < 1) {
								print("Choose 1, 2, 3, or 4!");
								break drawloop;
							}
							if (cardMulliganned > 4) {
								print("Choose 1, 2, 3, or 4!");
								break drawloop;
							}
						}

						// ok, so now we have a valid number. lets replace the card.
						print("redrawing" + cardMulliganned);
						insertCard(deck, hand.hand.get(cardMulliganned - 1));
						hand.hand.remove(cardMulliganned - 1);
						hand.hand.add(cardMulliganned - 1,drawCard(deck));

					}
			}

		}
	}
	
	public static void attackMinion(Minion attacker, Minion target, Variables v)
	{
		attacker.damage(target.damage);
		target.damage(attacker.damage);
		
		if(attacker.currenthealth <= 0)
		{
			destroyMinion(attacker, v);
		}
		
		if(target.currenthealth <= 0)
		{
			target.destroy();
		}
		
		attacker.canAttack = false;
		
		
	}
	
	

	
	public static void enemyTurn(Variables v)
	{
		v.enemymaxmana++;
		v.enemymana = v.enemymaxmana;
		
		//alright so have the enemy AI do stuff here. for now lets just make them play a Yeti.
		if(v.enemymana >= 4)
		{
			playMinion(v.enemyminions, new Minion(4,5,"Chillwind Yeti"));
		}

	}

	public static Minion minionFromCard(Card card)
	{
		MinionDatabase db = new MinionDatabase();
		return db.minionFromCard(card);
	}
	
	public static void destroyMinion(Minion minion, Variables v)
	{
		//first, trigger deathrattle
    	if(minion.name == "Squirrel")
    	{
    		print("Ha-hah! Squirrel found a card before it died.");
    		Card tempcard = drawCard(v.realtinkdeck);
			v.hand.draw(tempcard);
    	}
    	
		
		minion.destroy();
		
	}

}
