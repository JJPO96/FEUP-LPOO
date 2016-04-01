package maze.cli;

import java.util.Scanner;

import maze.exception.InvalidMazeSize;
import maze.exception.InvalidNumDragons;
import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

public class GameLauncherCLI {

	private static boolean done = false;
	private static Maze game;
	private static final String title = "\n   _____  _     _____   _      ____  ____  _____\n  /__ __\\/ \\ /|/  __/  / \\__/|/  _ \\/_   \\/  __/\n    / \\  | |_|||  \\    | |\\/||| / \\| /   /|  \\  \n    | |  | | |||  /_   | |  ||| |-||/   /_|  /_ \n    \\_/  \\_/ \\|\\____\\  \\_/  \\|\\_/ \\|\\____/\\____\\\n";
	private static final String youWin = "  ___  _ ____  _       _      _  _      _ \n  \\  \\///  _ \\/ \\ /\\  / \\  /|/ \\/ \\  /|/ \\\n   \\  / | / \\|| | ||  | |  ||| || |\\ ||| |\n   / /  | \\_/|| \\_/|  | |/\\||| || | \\||\\_/\n  /_/   \\____/\\____/  \\_/  \\|\\_/\\_/  \\|(_)";
	private static final String gameOver = "   _____ ____  _      _____   ____  _     _____ ____  _ \n  /  __//  _ \\/ \\__/|/  __/  /  _ \\/ \\ |\\/  __//  __\\/ \\\n  | |  _| / \\|| |\\/|||  \\    | / \\|| | //|  \\  |  \\/|| |\n  | |_//| |-||| |  |||  /_   | \\_/|| \\// |  /_ |    /\\_/\n  \\____\\\\_/ \\|\\_/  \\|\\____\\  \\____/\\__/  \\____\\\\_/\\_\\(_)";
	
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
				System.err.println("\nERROR:: Invalid option! Please try again!\n");
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
				System.err.println("ERROR:: Invalid value! Please try again!");
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
		
		int numMaxDragons = Maze.calculateMaxNumberDragons(mazeSize, Maze.minSize, Maze.minNumDragons);
		
		if (Maze.minNumDragons == numMaxDragons){
			System.out.println("\n		>> For the Maze size selected the maximum number of Dragons allowed is 1.\n");
			return numMaxDragons;
		}			
		
		System.out.println("\n		>> Choose the number of Dragons (Min: "+Maze.minNumDragons+" and Max: "+numMaxDragons+")!\n");
		int numDragons = getUserInput(scan, Maze.minNumDragons, numMaxDragons);
		
		return numDragons;
	}

	/**
	 * Returns the Maze's size selected by the user
	 * 
	 * @param scan to be used to read the user input
	 * @return Maze's size
	 */
	public static int getMazeSize(Scanner scan){
		
		System.out.println("\n		>> Choose Maze Size (Must be an odd number between "+Maze.minSize+" and "+Maze.maxSize+"):\n");
		int mazeSize = getUserInput(scan, Maze.minSize, Maze.maxSize);
		
		while (mazeSize % 2 == 0){ // The maze size can't be an even number
			System.err.println("ERROR:: Invalid value! Can't be an even number! Please try again!");
			mazeSize = getUserInput(scan, Maze.minSize, Maze.maxSize);
		}
		
		return mazeSize;
	}
		
	/**
	 * Displays the Main Menu
	 */
	public static void displayMainMenu(){
		System.out.println("\n		<< STARTING MENU >>\n");
		System.out.println("		>> 1 - Start game");
		System.out.println("		>> 2 - Exit");
		System.out.println();
	}
	
	/**
	 * Prints movement instructions
	 */
	public static void printInstructions(){
		System.out.println("\n		>> Keys for Movement: (A) - left, (D) - Right, (W) - Top, (S) - Down");
	}
	
	/**
	 * Prints game in text mode
	 */
	public static void printGame(){
		System.out.println("\n"+game);
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
	
	/**
	 * Runs the Game
	 * 
	 * @param scan to be used to read the user input
	 * @throws InvalidNumDragons 
	 * @throws InvalidMazeSize 
	 */
	public static void runGame(Scanner scan) throws InvalidMazeSize, InvalidNumDragons{

		int mazeSize = getMazeSize(scan);		
		int dragons = getNumberDragons(scan, mazeSize);		
		
		game = new Maze(menuGameMode(scan),dragons,mazeSize);
		
		printInstructions();
		printGame();

		while(game.isRunning()){
			game.update(getMoveInput(scan));
			printGame();
		}

		gameEnd(game);
	}

	public static void main(String[] args) throws InvalidMazeSize, InvalidNumDragons {

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



