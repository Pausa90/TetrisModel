package it.andclaval.tetris;
import java.awt.RenderingHints;

import it.andclaval.tetris.model.Matrix;
import it.andclaval.tetris.model.TetrisGame;
import it.andclaval.tetris.render.TextRendering;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TetrisGame game = new TetrisGame();
		TextRendering render = new TextRendering();
		Matrix matrix = game.getMatrix();
		
		game.startGame();
		render.rendering(matrix, game.getNextTetromino());

		boolean isGameOver;
		do {
			String command = render.input();
			if (command.equals("left") || command.equals("s"))
				game.traslateToLeft();
			else if (command.equals("right") || command.equals("f")) 
				game.traslateToRight();
			else if (command.equals("down") || command.equals("d")) 
				game.traslateToBelow();
			else if (command.equals("fall"))
				game.freeFall();
			else if (command.equals("clock") || command.equals("c"))
				game.rotateClockWise();
			else if (command.equals("anticlock") || command.equals("a"))
				game.rotateAntiClockWise();
			else if (command.equals("exit"))
				break;
			else if (command.equals("help"))
				System.out.println("" +
						"left || s \t translate current tetromino to left \n" +
						"right || f \t translate current tetromino to right \n" +
						"down || d \t translate current tetromino to below \n" +
						"fall \t to activate freefall() \n" +
						"clock || c \t rotate current tetromino to clock wise \n" +
						"anticlock || a \t rotate current tetromino to anticlock wise \n");
			else System.out.println("unknow command, try \"help\"");
			
			render.rendering(matrix, game.getNextTetromino());
			isGameOver = game.isEnd();
			
		}
		while(!isGameOver);
		System.out.println("Game Over!");
		
//		int[] a = {1,2,2,2,3,4,4,5,6};
//		print(a);
//		a = reverseAndUnique(a);
//		print(a);
	}
	
	public static int[] reverseAndUnique(int[] a) {
		int pred = a[0];
		int numRip = 0;
		for (int i = 1; i < a.length; i++){
			if (a[i]==pred)
				numRip++;
			pred = a[i];
		}
		int[] b = new int[a.length - numRip];
		pred = a[a.length-1];
		b[0] = a[a.length-1];
		int cell = 1;
		for (int i = 1; i < a.length; i++){
			System.out.println("i: " + i + " pos: " + (a.length-1-i) + " pred " + pred);
			if (a[a.length-1-i]!=pred){
				b[cell] = a[a.length-1-i];
				cell++;
			}
			pred = a[a.length-1-i];
		}
		return b;
	}
	
	public static void print(int[] a){
		for (int i : a)
			System.out.print(i + " ");
		System.out.println();
	}

}
