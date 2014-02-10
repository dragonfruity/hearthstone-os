import java.util.ArrayList;
import java.util.Stack;


public class Minion{
        
    //variables
	int maxhealth;
	int currenthealth;
	int damage;
	//int basedamage;
	String name;
	boolean canAttack = false;
	boolean windfury = false;
	boolean taunt = false;
	boolean divineshield = false;
	boolean charge = false;
	Stack<String> custom = new Stack<String>();
	String species;

    // the MountainBike subclass has
    // one constructor
    public Minion(int damagepassed, int healthpassed, String namepassed) {
        maxhealth = healthpassed;
        currenthealth = healthpassed;
        damage = damagepassed;
       // basedamage = damagepassed;
        name = namepassed;
    }   
        
    // the MountainBike subclass has
    // one method
    public void damage(int damage)
    {
        currenthealth = currenthealth - damage;
    }
    
    public void heal(int heal)
    {
    	currenthealth = currenthealth + heal;
    	
    	if(currenthealth > maxhealth)
    	{
    		currenthealth = maxhealth;
    	}
    }
    
    public void destroy()
    {
    	
    	//kill it with fire!
    	maxhealth = 1;
    	currenthealth = 1;
    	damage = 0;
    	//basedamage = 0;
    	name = "null";
    }
    
    public boolean isEnraged()
    {
    	if(currenthealth < maxhealth)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public int getDamage()
    {
    	int tempdamage = damage;
    	
    	if(name == "Amani Berserker")
    	{
    		if(isEnraged())
    		{
    			tempdamage = tempdamage + 3;
    		}
    	}
    	
    	for(int i = 0; i < custom.size(); i++)
    	{
    		if(custom.get(i).equalsIgnoreCase("Dark Iron Dwarf"))
        	{
        		tempdamage = tempdamage + 2;
        	}
        	if(custom.get(i).equalsIgnoreCase("Aldor Peacekeeper"))
        	{
        		tempdamage = 1;
        	}
    	}
    	
    	
    	
    	return tempdamage;
    }

}