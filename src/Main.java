import java.io.BufferedReader;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

















import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main{
	private static JTextArea textArea = new JTextArea(5,20);
	static JFrame frame = new JFrame("hearthstone-os");
	static JFrame inputframe = new JFrame("hearthstone-os");
	static JTextField textField = new JTextField(20);
	final static  List<String> holder = new LinkedList<String>();
	




	public static void main(String[] args) throws InterruptedException{

		// TODO Auto-generated method stub

		// who doesnt love some local variables!



		//final TextDemo b = new TextDemo();


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		inputframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputframe.setLayout(new BorderLayout());
		inputframe.setSize(200, 80);
		inputframe.setLocationRelativeTo(null);
		inputframe.setVisible(true);
		
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        
        textField = new JTextField(20);
        

        frame.add(textArea);
        inputframe.add(textField);
        
        textField.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            synchronized (holder) {
	                holder.add(textField.getText());
	                holder.notify();
	                textField.setText("");
	            }
	            //frame.dispose();
	        }
	    });


		Variables v = new Variables();

		
		
        
        


		// scanner for inputs
		Scanner input = new Scanner(System.in);
		
		synchronized (holder) {

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
				tinkdeck.push(new Card("Fireball", 4, false));
			} else {
				tinkdeck.push(new Card("Squirrel", 1, true));
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

	}


	
	public static Card drawCard(Deck deck) {
		int temprand = random(deck.deck.size() - 1);
		Card tempcard = deck.deck.get(temprand);
		deck.deck.remove(temprand);
		return tempcard;

	}
	
	public static void playerTurn(Variables v, Scanner input) throws InterruptedException
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
			
//			callCommand();
//			
//			while(command == true)
//			{
//				
//			}
//			
//			print(commandstring);
			
			
			//String inputs = getCommand("What action do you take?");
			
			while (holder.isEmpty())
	            holder.wait();

	        String inputs = holder.remove(0);

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
							msg(v,"That's not a number!");
							break playcardloop;
						}

						if(cardPlayed > v.hand.hand.size())
						{
							msg(v,"You don't have that many cards in your hand!");
							break playcardloop;
						}
						else
						{
							if(v.hand.hand.get(cardPlayed -1).manacost <= v.playermana)
							{
								if(v.hand.hand.get(cardPlayed-1).isMinion == true)
								{
									checknull:
										for(int i = 0; i < 6; i++)
										{
											if(v.playerminions[i].name == "null")
											{
												//print("played");
												v.playerminions[i] = minionFromCard(v.hand.hand.get(cardPlayed - 1));
												v.playermana = v.playermana - v.hand.hand.get(cardPlayed -1).manacost;

												v.hand.remove(cardPlayed - 1);

												break checknull;

											}
										}
								}
								else
								{
									v.playermana = v.playermana - v.hand.hand.get(cardPlayed -1).manacost;
									useSpell(v.hand.hand.get(cardPlayed-1),v);
									v.hand.remove(cardPlayed - 1);
								}
							}
							else
							{
								msg(v,"I don't have enough mana!");
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
							msg(v,"Thats not a number");
							break attackminionloop;
						}

						if(inputs.substring(9,13).equalsIgnoreCase("with"))
						{
							String number2 = inputs.substring(14,15);


							int attacker;

							try {
								attacker = Integer.parseInt(number2);
							} catch (NumberFormatException e) {
								msg(v,"Thats not a number");
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
											v.playerminions[attacker-1].canAttack = false;
										}
										else
										{
											msg(v,"that guy cant attack yet. wait a turn!");
										}
										
										
									}
									else
									{
										msg(v,"That's not a valid player minion.");
										break attackminionloop;
									}
								}
								else
								{
									msg(v,"comon, choose > 1");
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
												msg(v,"attacking " + v.enemyminions[target-1].name + " with " + v.playerminions[attacker-1].name);
												//call an attack function here! congrats it worked
												attackMinion(v.playerminions[attacker-1], v.enemyminions[target-1],v);
												v.playerminions[attacker-1].canAttack = false;
											}
											else
											{
												msg(v,"that guy cant attack yet. wait a turn!");
											}
										}
										else
										{
											msg(v,"thats not a valid player minion");
											break attackminionloop;
										}
									}
									else
									{
										msg(v,"comon, choose > 1");
										break attackminionloop;
									}

								}
								else
								{
									msg(v,"u wot m8");
									break attackminionloop;
								}
							}

							else
							{
								msg(v,"invalid enemy minion");
								break attackminionloop;
							}



						}
						else
						{
							msg(v,"Usage: attack _ with _");
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
			else
			{
				msg(v,"That wasn't even a command, dumbo. Commands: play, attack, endturn");
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
		clearConsole();//nl();//clearConsole();//print("Heres the current board!");

		if(v.info.equalsIgnoreCase("") == false)
		{
			print(v.info);
			v.info = "";
			nl();
		}


		print("Enemy controls:                                                                   Enemy Mana: " + v.enemymana + "/" + v.enemymaxmana);
		print("    0. Enemy has " + v.enemyhealth + "health");
		for (int i = 0; i < 6; i++) {
			if (enemyminions[i].name != "null") {

				if(enemyminions[i].currenthealth > 0)
				{

					print("    " + (i+1) + ". " + enemyminions[i].name + " can do " + enemyminions[i].damage + " damage, and has " + enemyminions[i].currenthealth + "/" + enemyminions[i].maxhealth + " health.");
				}
				else
				{
					enemyminions[i].destroy();
				}
			}
		}
		nl();

		print("You control:");
		print("    0. You has " + v.playerhealth + "health");
		for (int i = 0; i < 6; i++) {

			if(playerminions[i].currenthealth > 0)
			{

				if (playerminions[i].name != "null") {
					print("    " + (i+1) + ". " + playerminions[i].name + " can do " + playerminions[i].damage + " damage, and has " + playerminions[i].currenthealth + "/" + playerminions[i].maxhealth + " health.");
				}
				else
				{
					playerminions[i].destroy();
				}
			}
		}
		nl();
		print("Your hand has:                                                                       Mana: " + v.playermana + "/" + v.playermaxmana);
		for (int i = 0; i < hand.hand.size(); i++) {
			print(i + 1 + ". " + hand.hand.get(i).name + " (" + hand.hand.get(i).manacost + ")");
		}

	}


	
	public static void updateBoard(Variables v)
	{
		updateBoard(v.playerhealth,v.enemyhealth,v.playerminions,v.enemyminions,v.hand,v);
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

	
	public static void msg(Variables v, String string)
	{
		v.info = string;
	}
	
	private static void print(String s) {
		System.out.println(s);
        textArea.append(s + "\n");
	}

	private static void print(int s) {
		System.out.println(s);
		
	}
	
	public static void clearConsole()
	{
		textArea.setText("");
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


	public static Hand startingHand(Hand hand, Deck deck, Scanner input, boolean isFirst) throws InterruptedException
	{
		for (int i = 0; i < 3; i++) {
			Card tempcard = drawCard(deck);
			hand.draw(tempcard);

		}

		while (true) {
			clearConsole();
			print("Here's your starting hand! You go first.");
			for (int i = 0; i < 3; i++) {
				print(i + 1 + ". " + hand.hand.get(i).name + " (" + hand.hand.get(i).manacost + ")");
			}

			String inputs = null;
			print("Type ready once you're ready to go! Or, type redraw and a number to mulligan the card.");
			//prepareInput();
			
			
			//inputs = getCommand("What action do you take?");
			while (holder.isEmpty())
	            holder.wait();

	        inputs = holder.remove(0);
			
			
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
	
	public static Minion targetMinion(Variables v) throws InterruptedException
	{

		boolean isEnemy;

		playerorenemyloop:
			while(true)
			{
				updateBoard(v);

				print("Target enemy minion, player minion, or the FACE?");

				while (holder.isEmpty())
					holder.wait();

				String inputs = holder.remove(0);

				if(inputs.equalsIgnoreCase("enemy"))
				{
					isEnemy = true;
					break playerorenemyloop;
				}
				else if(inputs.equalsIgnoreCase("player"))
				{
					isEnemy = true;
					break playerorenemyloop;
				}
				else if(inputs.equalsIgnoreCase("face"))
				{
					return new Minion(80, 80, "Face");
				}

			}
		while(true)
		{

			updateBoard(v);

			print("Target which minion?");

			while (holder.isEmpty())
				holder.wait();

			String inputs = holder.remove(0);

			int target = Integer.parseInt(inputs);

			if(isEnemy == true)
			{

				if(v.enemyminions[target-1].name.equalsIgnoreCase("null") == false)
				{
					
					return v.enemyminions[target-1];
				}
				else
				{
					msg(v,"That wasn't a good target.");
				}
			}
			else
			{
				if(v.enemyminions[target-1].name.equalsIgnoreCase("null") == false)
				{
					return v.enemyminions[target-1];
				}
				else
				{
					msg(v,"That wasn't a good target.");
				}
			}

		}
	}
	
	public static void useSpell(Card card, Variables v) throws InterruptedException
	{
		//this is gonna be a long database of spells. good luck.
		if(card.name.equalsIgnoreCase("Fireball"))
		{
			Minion tempminion = targetMinion(v);
			if(tempminion.name.equalsIgnoreCase("Face") == false)
			{
				tempminion.damage(6);
			}
			else
			{
				v.enemyhealth = v.enemyhealth - 6;
			}
		}
		
		
		
		
		
	}
	
	
	
}