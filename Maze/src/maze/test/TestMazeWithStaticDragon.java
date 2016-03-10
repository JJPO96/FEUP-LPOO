package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

public class TestMazeWithStaticDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', ' ', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', ' ', ' ', ' ', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	Hero hero = new Hero(3, 1, 'H');
	Dragon dragon = new Dragon(3, 3, 'D');
	Sword sword = new Sword(1, 3, 'E');
	
	@Test
	public void testMoveHeroToFreeCell() {
		Maze maze = new Maze(m1, Mode.BEGGINER, hero, dragon, sword);
		assertEquals(new Position(3, 1), maze.getHero().getPos());		
		maze.updateHero(Direction.LEFT);
		assertEquals(new Position(2, 1), maze.getHero().getPos());
	}
	
	@Test
	public void testHeroDies() {
		Maze maze = new Maze(m1, Mode.BEGGINER, hero, dragon, sword);
		assertEquals(false, maze.getHero().isArmed());
		maze.updateHero(Direction.DOWN);
		assertEquals(false, maze.getHero().isAlive());
	}
	
	@Test
	public void testPickSword() {
		Direction[] movement = {Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN};
		Maze maze = new Maze(m1, Mode.BEGGINER, hero, dragon, sword);
		assertEquals(false, maze.getHero().isArmed());
		
		for(Direction move: movement)
			maze.updateHero(move);
		
		assertEquals(maze.getSword().getPos(), maze.getHero().getPos());
		assertEquals(true, maze.getSword().isPicked());
		assertEquals(true, maze.getHero().isArmed());
	}
	
	@Test
	public void testDragondies() {
		Direction[] movement = {Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN,
				Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.DOWN};
		Maze maze = new Maze(m1, Mode.BEGGINER, hero, dragon, sword);
		
		assertEquals(true, maze.getHero().isAlive());
		assertEquals(true, maze.getDragon().isAlive());
		assertEquals(false, maze.getHero().isArmed());
		
		for(Direction move: movement)
			maze.updateHero(move);
		
		assertEquals(true, maze.getHero().isAlive());
		assertEquals(false, maze.getDragon().isAlive());
		assertEquals(true, maze.getHero().isArmed());
	}
	
	@Test
	public void testHeroExitDragonAlive() {
		Direction[] movement = {Direction.RIGHT, Direction.UP, Direction.RIGHT};
		Maze maze = new Maze(m1, Mode.BEGGINER, hero, dragon, sword);
				
		for(Direction move: movement)
			maze.updateHero(move);	
		
		assertEquals(true, true);
		assertEquals(true, maze.getDragon().isAlive());
		assertEquals(true, maze.isRunning());
		assertNotEquals(maze.getgameBoard().getExitPos(), maze.getHero().getPos());		
	}
	
	@Test
	public void testWin() {
		Direction[] movement = {Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN,
				Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.DOWN,
				Direction.UP, Direction.RIGHT};
		Maze maze = new Maze(m1, Mode.BEGGINER, hero, dragon, sword);
	
		assertEquals(true, maze.isRunning());
		
		for(Direction move: movement)
			maze.updateHero(move);
		
		assertEquals(false, maze.isRunning());
		assertEquals(maze.getgameBoard().getExitPos(), maze.getHero().getPos());
		assertEquals(true, maze.getSword().isPicked());
		assertEquals(true, maze.getHero().isArmed());
		assertEquals(true, maze.getHero().isAlive());
		assertEquals(false, maze.getDragon().isAlive());
	}
}

