package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

public class TestSomeRandomBehavior {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', ' ', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	
	Hero hero = new Hero(3, 1, 'H');
	Dragon dragon = new Dragon(3, 3, 'D');
	Sword sword = new Sword(1, 3, 'E');

	@Test
	public void testModes() {
		Maze mazeOne = new Maze(m1, Mode.INTERMEDIATE, hero, dragon, sword);
		assertEquals(Mode.INTERMEDIATE, mazeOne.getMode());
		Maze mazeTwo = new Maze(m1, Mode.EXPERT, hero, dragon, sword);
		assertEquals(Mode.EXPERT, mazeTwo.getMode());
	}
	
	//TODO É PRECISO FAZER MAIS TESTES PARA COBRIR CASOS EM QUE O DRAGÃO
	// PODE DORMIR OU MOVER/DORMIR
}
