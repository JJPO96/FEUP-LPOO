package maze.cli;

import java.util.Scanner;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;
import maze.logic.Maze.Token;

public class GameLauncher {

	private static boolean done = false;

	public static String convertString(Maze game){

		Position pos = new Position(0, 0);

		String maze = "";

		for (int i = 0; i < game.getgameBoard().getBoard().length; i++){
			for ( int j = 0; j < game.getgameBoard().getBoard()[i].length; j++){
				pos.setX(j);
				pos.setY(i);

				if (game.getHero().getPos().equals(pos))
					maze+= game.getHero().getSymbol();
				else if (game.getDragon().isAlive() && game.getDragon().getPos().equals(pos) && game.getDragon().getPos().equals(game.getSword().getPos()))
					maze += Token.DRAGSWORD.getSymbol();
				else if (game.getDragon().isAlive() && game.getDragon().getPos().equals(pos))
					maze+= game.getDragon().getSymbol();
				else if (game.getSword().getPos().equals(pos) && !game.getSword().isPicked())
					maze+= game.getSword().getSymbol();
				else
					maze += game.getgameBoard().getBoard()[i][j];
			}

			maze += "\n";
		}		

		return maze;		
	}

	/**
	 * Prints game's end message
	 * 
	 * @param game
	 */
	public static void gameEnd(Maze game){
		if (game.isMazeOpen())
			System.out.println("VICTORY!");
		else
			System.out.println("DEFEAT!");
	}

	public static void printMaze(Maze game){	
		System.out.println("\n"+convertString(game));
	}

	/**
	 * Verifies if the move command is valid
	 * 
	 * @param input which is the move command made by the user
	 * @return if the move was valid or not
	 */
	public static boolean validMove(char input){

		// Movement - (L)eft, (R)ight, (U)p, (D)own		
		switch (input){
		case 'L':
			return true;
		case 'R':
			return true;
		case 'U':
			return true;
		case 'D':
			return true;
		default:
			return false;
		}
	}

	/**
	 * Selects game mode
	 * 
	 * @param scan to be used to get input from user
	 * @return game mode selected
	 */
	public static Mode menuGameMode(Scanner scan){
		Mode mode = null;

		System.out.println("\n<< SELECT GAME MODE >>\n");
		System.out.println("> 1 - EASY");
		System.out.println("> 2 - INTERMEDIATE");
		System.out.println("> 3 - EXPERT");
		System.out.println();

		int input = getUserInput(scan, 1, 3);	

		switch (input){
		case 1:
			mode = Mode.BEGGINER;
			break;
		case 2:
			mode = Mode.INTERMEDIATE;
			break;
		case 3:
			mode = Mode.EXPERT;
			break;
		}

		return mode;
	}

	public static int getUserInput(Scanner scan, int min, int max){

		int input = 0;

		do {

			try{
				input = scan.nextInt();
				if (input < min || input > max)
					throw new IllegalArgumentException();
			}

			catch(Exception e) {
				scan.nextLine();
				System.err.println("ERROR:: Invalid option! Please try again!");
			} 
		} while (input < min || input > max);

		scan.nextLine();
		
		return input;
	}
	
	public static Direction getMoveInput(Scanner scan){

		char input = ' ';
		Direction move = null;

		do {
			try{
				input = scan.nextLine().charAt(0);
				input = Character.toUpperCase(input);
				if(!validMove(input))
					throw new IllegalArgumentException();
			}

			catch(Exception e) {
				System.err.println("ERROR:: Invalid option! Please try again!");
			}			
		} while (!validMove(input));
		
		switch (input){
		case 'L':
			move = Direction.LEFT;
			break;
		case 'R':
			move = Direction.RIGHT;
			break;
		case 'U':
			move = Direction.UP;
			break;
		case 'D':
			move = Direction.DOWN;
			break;
		}
		
		return move;
	}

	public static void runGame(Scanner scan){

		Maze game = new Maze(menuGameMode(scan));		
		printMaze(game);

		while(game.isRunning()){
			game.update(getMoveInput(scan));
			printMaze(game);
		}

		gameEnd(game);
	}
	
	public static void displaMainMenu(){
		System.out.println("\n<< STARTING MENU >>\n");
		System.out.println("> 1 - Start game");
		System.out.println("> 2 - Exit");
		System.out.println();
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int input = 0;

		System.out.println("<< WELCOME TO THE MAZE >>\n");

		while (!done){			
			displaMainMenu();
			input = getUserInput(scan, 1, 2);			

			switch(input){
			case 1:
				runGame(scan);
				break;
			case 2:
				done = true;
				break;			
			}
		}

		System.out.println("EXITING...");
		scan.close();
		
		/*MazeBuilder maze = new MazeBuilder();
		maze.buildMaze(9);
		System.out.println(maze);*/
	}
}


