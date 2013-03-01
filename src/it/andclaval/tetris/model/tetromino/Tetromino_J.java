package it.andclaval.tetris.model.tetromino;

public class Tetromino_J extends Tetromino {

	public Tetromino_J(){
		super();
		int[][] shape = this.getShape();
		for (int i=0; i<this.COLUMN; i++){
			shape[0][i] = this.T_FREE;
			shape[1][i] = this.T_FREE;
		}
		shape[0][3]=this.T_OCCUPIED;
		shape[1][3]=this.T_OCCUPIED;
		shape[1][2]=this.T_OCCUPIED;
		shape[1][1]=this.T_OCCUPIED;
	}
}
