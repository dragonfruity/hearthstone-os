
public class Minion{
        
    //variables
	int maxhealth;
	int currenthealth;
	int damage;
	String name;
	boolean canAttack = false;

    // the MountainBike subclass has
    // one constructor
    public Minion(int damagepassed, int healthpassed, String namepassed) {
        maxhealth = healthpassed;
        currenthealth = healthpassed;
        damage = damagepassed;
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
    	name = "null";
    }

}