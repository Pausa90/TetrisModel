package it.andclaval.tetris;
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
		
	/*	game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());*/
		
/*		game.traslateToLeft();
		render.rendering(matrix.getMatrix());
		game.traslateToLeft();
		render.rendering(matrix.getMatrix());
		game.traslateToLeft();
		render.rendering(matrix.getMatrix());
		game.traslateToLeft();
		render.rendering(matrix.getMatrix());
		
		
		game.traslateToRight();
		render.rendering(matrix.getMatrix());
		game.traslateToRight();
		render.rendering(matrix.getMatrix());
		game.traslateToRight();
		render.rendering(matrix.getMatrix());
		game.traslateToRight();
		render.rendering(matrix.getMatrix());
		game.traslateToRight();
		render.rendering(matrix.getMatrix());
		game.traslateToRight();
		render.rendering(matrix.getMatrix());
		game.traslateToRight();
		render.rendering(matrix.getMatrix()); */
		
		
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		game.traslateToBelow();
		render.rendering(matrix.getMatrix());
		
		game.rotateClockWise();
		render.rendering(matrix.getMatrix());
		game.rotateAntiClockWise();
		render.rendering(matrix.getMatrix());
		
		
	}

}
