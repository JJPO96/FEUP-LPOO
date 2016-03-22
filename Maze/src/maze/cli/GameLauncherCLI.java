package maze.cli;

import java.util.Scanner;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

public class GameLauncherCLI {

	private static boolean done = false;
	private static final int minSize = 5;
	private static final int maxSize = 27;
	private static final int minNumDragons = 1;
	private static final String title = "\n   _____  _     _____   _      ____  ____  _____\n  /__ __\\/ \\ /|/  __/  / \\__/|/  _ \\/_   \\/  __/\n    / \\  | |_|||  \\    | |\\/||| / \\| /   /|  \\  \n    | |  | | |||  /_   | |  ||| |-||/   /_|  /_ \n    \\_/  \\_/ \\|\\____\\  \\_/  \\|\\_/ \\|\\____/\\____\\\n";
	private static final String youWin = "  ___  _ ____  _       _      _  _      _ \n  \\  \\///  _ \\/ \\ /\\  / \\  /|/ \\/ \\  /|/ \\\n   \\  / | / \\|| | ||  | |  ||| || |\\ ||| |\n   / /  | \\_/|| \\_/|  | |/\\||| || | \\||\\_/\n  /_/   \\____/\\____/  \\_/  \\|\\_/\\_/  \\|(_)";
	private static final String gameOver = "   _____ ____  _      _____   ____  _     _____ ____  _ \n  /  __//  _ \\/ \\__/|/  __/  /  _ \\/ \\ |\\/  __//  __\\/ \\\n  | |  _| / \\|| |\\/|||  \\    | / \\|| | //|  \\  |  \\/|| |\n  | |_//| |-||| |  |||  /_   | \\_/|| \\// |  /_ |    /\\_/\n  \\____\\\\_/ \\|\\_/  \\|\\____\\  \\____/\\__/  \\____\\\\_/\\_\\(_)";
	
	/**
	 * Calculates the maximum number of dragons for a certain Maze's size
	 * 
	 * @param size fot he Maze
	 * @return the number of dragons
	 */
	public static int calculateMaxNumberDragons(int size){		
		return minNumDragons+(size-minSize)/2;
	}

	/**
	 * Verifies if the move command is valid
	 * 
	 * @param input which is the move command made by the user
	 * @return true if the move was valid
	 */
	public static boolean validMove(char input){

		// Movement - (A) - left, (D) - Right, (W) - Top, (S) - Down		
		switch (input){
		case 'A':
			return true;
		case 'D':
			return true;
		case 'W':
			return true;
		case 'S':
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

		System.out.println("\n		<< SELECT GAME MODE >>\n");
		System.out.println("		>> 1 - STATIC DRAGONS");
		System.out.println("		>> 2 - MOVING DRAGONS");
		System.out.println("		>> 3 - MOVING AND SLEEPING DRAGONS\n");;

		int input = getUserInput(scan, 1, 3);	

		switch (input){
		case 1:
			mode = Mode.STATIC;
			break;
		case 2:
			mode = Mode.MOVING;
			break;
		case 3:
			mode = Mode.MOVINGSLEEPING;
			break;
		}

		return mode;
	}

	/**
	 * Reads the user input
	 * 
	 * @param scan to be used to get input from user
	 * @param min value
	 * @param max value
	 * @return user input
	 */
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
	
	/**
	 * Gets the movement directions
	 * 
	 * @param scan to be used to read the user input
	 * @return direction
	 */
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
		case 'A':
			move = Direction.LEFT;
			break;
		case 'D':
			move = Direction.RIGHT;
			break;
		case 'W':
			move = Direction.UP;
			break;
		case 'S':
			move = Direction.DOWN;
			break;
		}
		
		return move;
	}
	
	/**
	 * Returns the number of dragons of the Maze selected by the user
	 * 
	 * @param scan to be used to read the user input
	 * @param mazeSize
	 * @return number of dragons
	 */
	public static int getNumberDragons(Scanner scan, int mazeSize){
		
		System.out.println("\n		>> Choose number of Dragons!\n");
		int numDragons = getUserInput(scan, minNumDragons, calculateMaxNumberDragons(mazeSize));
		
		return numDragons;
	}

	/**
	 * Returns the Maze's size selected by the user
	 * 
	 * @param scan to be used to read the user input
	 * @return Maze's size
	 */
	public static int getMazeSize(Scanner scan){
		
		System.out.println("\n		>> Choose Maze Size (Min: "+minSize+" - Max: "+maxSize+"):\n");
		int mazeSize = getUserInput(scan, minSize, maxSize);
		
		return mazeSize;
	}
	
	/**
	 * Runs the Game
	 * 
	 * @param scan to be used to read the user input
	 */
	public static void runGame(Scanner scan){

		int mazeSize = getMazeSize(scan);		
		int dragons = getNumberDragons(scan, mazeSize);		
		
		Maze game = new Maze(menuGameMode(scan),dragons,mazeSize);		
		System.out.println("\n"+game);

		while(game.isRunning()){
			game.update(getMoveInput(scan));
			System.out.println("\n"+game);
		}

		gameEnd(game);
	}
	
	/**
	 * Displys the Main Menu
	 */
	public static void displayMainMenu(){
		System.out.println("\n		<< STARTING MENU >>\n");
		System.out.println("		>> 1 - Start game");
		System.out.println("		>> 2 - Exit");
		System.out.println();
	}
	
	/**
	 * Prints game's end message
	 * 
	 * @param game
	 */
	public static void gameEnd(Maze game){
		if (game.isMazeOpen())
			System.out.println(youWin);
		else
			System.out.println(gameOver);
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int input = 0;
		System.out.println(title);
		
		while (!done){			
			displayMainMenu();
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
	}
}



