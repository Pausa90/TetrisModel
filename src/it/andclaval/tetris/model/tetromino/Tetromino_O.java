package it.andclaval.tetris.model.tetromino;

import it.andclaval.tetris.tool.Couple;

public class Tetromino_O extends Tetromino {

	public Tetromino_O(){
		super();
		int[][] shape = this.getShape();
		for (int i=0; i<this.COLUMN; i++){
			shape[0][i] = this.T_FREE;
			shape[1][i] = this.T_FREE;
		}
		shape[0][1]=this.T_OCCUPIED;
		shape[0][2]=this.T_OCCUPIED;
		shape[1][2]=this.T_OCCUPIED;
		shape[1][1]=this.T_OCCUPIED;
		
		this.pivot = new Couple<Integer>(-1, -1);
	}
}
