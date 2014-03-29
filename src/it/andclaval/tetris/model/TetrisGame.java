package it.andclaval.tetris.model;

import it.andclaval.tetris.model.tetromino.*;

public class TetrisGame {
	/** da fare singleton **/
	private Matrix matrix;
	private boolean end;
	private Tetromino current;
	private Tetromino next;

	public TetrisGame(){
		this.matrix = new Matrix(this);
		this.end = false;
	}
	
	public boolean isEnd() {
		return end;
	}
	
	public void setEnd(boolean end) {
		this.end = end;
	}
	
	public Matrix getMatrix() {
		return matrix;
	}
	
	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
	
	public String getCurrentTetrominoName(){
		return this.current.toString();
	}
	
	public int getCurrentLevel(){
		return this.matrix.getLevel();
	}
	
	public String getNextTetromino(){
		return this.next.toString();
	}

	/** Metodo di avvio del gioco **/
	public void startGame(){
		this.next = this.createRandomTetromino();
		this.startNextTetromino();
	}
	
	public void startNextTetromino(){
		this.current = this.next;
		this.next = this.createRandomTetromino();
		this.matrix.putTetromino(current);
	}
	
	public boolean update(){
		return this.matrix.update();
	}
	
	public void rotateClockWise(){
		this.matrix.rotateCurrent(true);
	}
	
	public void rotateAntiClockWise(){
		this.matrix.rotateCurrent(false);
	}
	
	public void traslateToLeft(){
		this.matrix.traslateCurrent(true);
	}
	
	public void traslateToRight(){
		this.matrix.traslateCurrent(false);
	}
	
	public void traslateToBelow(){
		//riazzerare il tempo
		this.matrix.update();
	}
	
	/** Se il pezzo deve cadere giù **/
	public void freeFall(){
		boolean isFallen;
		do{
			isFallen = this.matrix.update();
		} while (isFallen);
	}

	/* da inserire una creazione con probabilità */
	private Tetromino createRandomTetromino() {
		int rand = (int) (7*Math.random()) + 1;
		switch (rand){
			case 1: 
				return new Tetromino_I();
			case 2: 
				return new Tetromino_J();
			case 3: 
				return new Tetromino_L();
			case 4: 
				return new Tetromino_O();
			case 5: 
				return new Tetromino_S();
			case 6: 
				return new Tetromino_T();
			case 7: 
				return new Tetromino_Z();
		}
		return null;
	}
	
	/*
	 * Due TetrisGame sono uguali se hanno end uguale e la stessa matrice
	 */
	public boolean equals(Object o){
		TetrisGame tetrisGame = (TetrisGame) o;
		return this.matrix.equals(tetrisGame.getMatrix()) && this.end == tetrisGame.end; 
	}
}
