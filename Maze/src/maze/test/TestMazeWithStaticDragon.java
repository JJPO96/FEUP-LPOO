package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

public class TestMazeWithStaticDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Confirms that Hero's assigned position is correct
		assertEquals(new Position(3, 1), maze.getHero().getPos());
		
		// Moves Hero to the left
		maze.updateHero(Direction.LEFT);
		
		// Confirms Hero new position after moving to the left
		assertEquals(new Position(2, 1), maze.getHero().getPos());
	}
	
	@Test
	public void testHeroDies() {
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Confirms that Hero starts as being alive
		assertEquals(true, maze.getHero().isAlive());
		
		// Confirms that Hero is not armed
		assertEquals(false, maze.getHero().isArmed());
		
		// Moves the Hero next to the Dragon
		maze.updateHero(Direction.DOWN);
		
		// Confirms that the Hero has died
		assertEquals(false, maze.getHero().isAlive());
	}
	
	@Test
	public void testPickSword() {
		Direction[] movement = {Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN};
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Confirms that Hero is not starting as armed
		assertEquals(false, maze.getHero().isArmed());
		
		// Moves the Hero
		for(Direction move: movement)
			maze.updateHero(move);
		
		// Confirms Hero's position is the same as the Sword one
		assertEquals(maze.getSword().getPos(), maze.getHero().getPos());
		
		// Confirms that the Sword is picked
		assertEquals(true, maze.getSword().isPicked());
		
		// Confirms that the Hero is armed
		assertEquals(true, maze.getHero().isArmed());
	}
	
	@Test
	public void testDragonDies() {
		Direction[] movement = {Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN,
				Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.DOWN};
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Confirms that the Hero is alive
		assertEquals(true, maze.getHero().isAlive());
		
		// Confirms that the Hero isalive
		assertEquals(true, maze.hasDragonsAlive());
		
		// Confirms that the Hero is not starting armed
		assertEquals(false, maze.getHero().isArmed());
		
		// Moves the Hero next to the Dragon, after getting the Sword
		for(Direction move: movement)
			maze.updateHero(move);
		
		// Confirms that the Hero picked the sword
		assertEquals(true, maze.getHero().isArmed());
		
		// Confirms that the Hero is still aive after fighting against the Dragon with the Sword
		assertEquals(true, maze.getHero().isAlive());
		
		// Confirms that the Dragon is dead
		assertEquals(false, maze.hasDragonsAlive());			
	}
	
	@Test
	public void testHeroExitDragonAlive() {
		Direction[] movement = {Direction.RIGHT, Direction.UP, Direction.RIGHT};
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Moves the Hero to the Exit of the Maze
		for(Direction move: movement)
			maze.updateHero(move);	
		
		// Confirms thatthere is any Dragon alive (game can't finish with any remaining alive Dragon)
		assertEquals(true, maze.hasDragonsAlive());
		
		// Confirms that the Game didn't finish
		assertEquals(true, maze.isRunning());
		
		// Confirms that the Hero correctly couldn't go to the exit, because there is still an alive Dragon
		assertNotEquals(maze.getGameBoard().getExitPos(), maze.getHero().getPos());		
	}
	
	@Test
	public void testWin() {
		Direction[] movement = {Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN,
				Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.DOWN,
				Direction.UP, Direction.RIGHT};
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Confirms that the game is still running
		assertEquals(true, maze.isRunning());
		
		// Moves the Hero to the Exit of the Maze, after picking the sword and killing the Dragon
		for(Direction move: movement)
			maze.updateHero(move);
		
		// Confirms that the Hero picked the Sword
		assertEquals(true, maze.getHero().isArmed());
		
		// Confirms that the Dragon was killed by the Hero
		assertEquals(false, maze.hasDragonsAlive());
		
		// Confirms that the Hero exit the Maze
		assertEquals(true, maze.isMazeOpen());
		
		// Verifies the Game has finished
		assertEquals(false, maze.isRunning());
	}
	
	@Test
	public void testMazetoString(){
		Maze maze = new Maze(m1, Mode.STATIC);
		
		// Confirms that the toString() method is correctly working for the class Maze
		assertEquals(" X X X X X\n X     H S\n X   X   X\n X E   D X\n X X X X X\n", maze + "");		
	}
}

