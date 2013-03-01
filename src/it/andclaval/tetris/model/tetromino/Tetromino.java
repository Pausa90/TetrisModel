package it.andclaval.tetris.model.tetromino;

public abstract class Tetromino {
	protected final int ROW = 2;
	protected final int COLUMN = 4;
	private int[][] shape; 
	protected final int T_FREE = 0;
	protected final int T_OCCUPIED = 2;
	
	public Tetromino(){
		
		this.shape = new int[ROW][COLUMN]; //all'inizio i pezzi sono sempre in orizzontale
	}

	public int[][] getShape() {
		return shape;
	}

	public void setShape(int[][] shape) {
		this.shape = shape;
	}
	
	
}
