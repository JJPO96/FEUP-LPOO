package maze.cli;

import java.util.Scanner;
import maze.logic.*;

public class Jogo {
	
	public static void mostraTab(Maze game){
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				System.out.print(game.getLab().getTab()[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean validMove(char input){
		
		switch (input){
		case 'E':
			return true;
		case 'e':
			return true;
		case 'D':
			break;
		case 'd':
			return true;
		case 'C':
			return true;
		case 'c':
			return true;
		case 'B':
			return true;
		case 'b':
			return true;
		default:
			break;
		}
		return false;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		char input;
		
		Maze game = new Maze();
		
		// TODO - Quando se criar varios modos de jogo, a função init() irá receber um parâmeto
		//  que irá dizer o modo de jogo escolhido
		game.init();		
		mostraTab(game);
		
		while(game.isRunning()){
			//APAGAR -  A cada comando, o programa atualiza e mostra a nova situação do labirinto
			
			// Movimento - E ou e (Esquerda), D ou d (Direita), C ou c (Cima), B ou b (Baixo)
			
			input = scan.nextLine().charAt(0);
			
			if (validMove(input)){
				game.update(input);
				mostraTab(game);
			}
		}		
		
		scan.close();
	}
}

