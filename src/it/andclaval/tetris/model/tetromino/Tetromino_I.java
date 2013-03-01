package it.andclaval.tetris.model.tetromino;

public class Tetromino_I extends Tetromino {

	public Tetromino_I(){
		super();
		for (int i=0; i<this.COLUMN; i++){
			this.getShape()[0][i] = this.T_FREE;
			this.getShape()[1][i] = this.T_OCCUPIED;
		}
	}
}