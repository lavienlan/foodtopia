/** 
 * This is the Building class of the Fantasy Game in OOP 3
 * The Building class is a subclass of Place
 * Buildings have certain properties that Elves can interact with.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 04/21/2023
 * @author Monellie Ghaffari and Rachel Jing
 */

import java.util.ArrayList;

class Building extends Place {
	
    /** Variable Declaration */
	boolean fountain;
   boolean muggers;
   
	/**
	 * Constructs a Building
	 * @param name The name of the building.
	 * @param gold The amount of the gold.
	 * @param fountain If there is a fountain
	 * @param muggers If there is a mugger present
	 */
	Building(String name, int gold, boolean fountain, boolean muggers)
	{
      super(name, gold);
		this.fountain = fountain;
      this.muggers = muggers;
	}
   
   /**
    * @Override
	 * Have Creature interact with Building and check for a fountain and muggers
    *
    * @param creat the creature entering the building
    * @return void
    */
   void enter (Creature creat) {
      super.enter(creat);
		if (fountain)
			creat.useWater();
      if (muggers)
         creat.getMugged();
   }
}


