package it.andclaval.tetris.model.tetromino;

import it.andclaval.tetris.tool.Couple;

public abstract class Tetromino {
	protected final int ROW = 2;
	protected final int COLUMN = 4;
	private int[][] shape; 
	protected final int T_FREE = 0;
	protected final int T_OCCUPIED = 2;
	protected Couple<Integer> pivot;
	
	public Tetromino(){
		
		this.shape = new int[ROW][COLUMN]; //all'inizio i pezzi sono sempre in orizzontale
		this.pivot = new Couple<Integer>(1, 2); //vale per tutti tranne O ed I
	}

	public int[][] getShape() {
		return shape;
	}

	public void setShape(int[][] shape) {
		this.shape = shape;
	}
	
	public Couple<Integer> getPivot(){
		return this.pivot;
	}
	
}
