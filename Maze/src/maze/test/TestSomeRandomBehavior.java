package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Maze.Mode;

public class TestSomeRandomBehavior {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	
	@Test(timeout=1000)
	public void testDragonMovingMode() {

		Maze maze = new Maze(m1, Mode.MOVING);

		// Confirms that the Maze is initialized in the correct mode selected
		assertEquals(Mode.MOVING, maze.getMode());		
		
		// Saves Dragon initial position
		Position startingPos = new Position (maze.getDragons().get(0).getPos().getX(), maze.getDragons().get(0).getPos().getY());
			
		// Confirms that the Hero is alive at the start of the Game
		assertEquals(true, maze.getHero().isAlive());
		
		boolean move = false, killHero = false;
		
		while (!move || !killHero) {

			maze.updateDragons();			

			// Verifies if the Dragon has moved
			if (!maze.getDragons().get(0).getPos().equals(startingPos))
				move = true;
			
			// Verifies if the Dragon has killed the Hero
			else if (!maze.getHero().isAlive())
				killHero = true;
		}
	}
	
	
	@Test(timeout=1000)
	public void testDragonMovingSleepingMode() {

		Maze maze = new Maze(m1, Mode.MOVINGSLEEPING);

		// Confirms that the Maze is initialized in the correct mode selected
		assertEquals(Mode.MOVINGSLEEPING, maze.getMode());		
		
		// Saves Dragon initial position
		Position startingPos = new Position (maze.getDragons().get(0).getPos().getX(), maze.getDragons().get(0).getPos().getY());
		
		// Confirms that Dragon is not sleeping at the start of the Game
		assertEquals(false, maze.getDragons().get(0).isSleeping());
		
		// Confirms that the Hero is alive at the start of the Game
		assertEquals(true, maze.getHero().isAlive());
		
		boolean move = false, sleep = false, killHero = false;
		
		while (!move || !sleep || !killHero) {

			maze.updateDragons();			

			// Verifies if the Dragon has moved
			if (!maze.getDragons().get(0).getPos().equals(startingPos))
				move = true;
			
			// Verifies if the Dragon has falles asleep
			else if (maze.getDragons().get(0).isSleeping())
				sleep = true;
			
			// Verifies if the Dragon has killed the Hero
			else if (!maze.getHero().isAlive())
				killHero = true;
		}
	}
}