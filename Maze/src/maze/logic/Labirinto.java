package maze.logic;

public class Labirinto {
	
	private static char path = ' ';
	private static char exit = 'S';
	private char[][] tabuleiro = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};
	
	public Labirinto(){};
	
	public char[][] getTab(){
		return tabuleiro;
	}
	
	/**
	 * Verifies if an element collides with other element/wall of the labyrinth
	 * 
	 * @param x coordinate of the element
	 * @param y coordinate of the element
	 * @return true if collides with some element
	 */
	
	public boolean checkCollision(int x, int y){
		// TODO - Falta corrigir (verificar caso de colisão com dragao/espada/saida)
		
		if (tabuleiro[y][x] == path)
			return false;		
		
		return true;
	}
	
	/*public boolean checkExit(Coordenadas pos){
		// TODO -  VERIFICAR SE O HEROI CHEGOU À SAIDA
	}*/
}