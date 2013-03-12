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
		// TODO Auto-generated method stub
		
		TetrisGame game = new TetrisGame();
		TextRendering render = new TextRendering();
		Matrix matrix = game.getMatrix();
		
		game.startGame();
		render.rendering(matrix.getMatrix());

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
			
			render.rendering(matrix.getMatrix());
			isGameOver = game.isEnd();
			
		}
		while(!isGameOver);
		System.out.println("Game Over!");
	}

}
