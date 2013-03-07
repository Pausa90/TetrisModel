package it.andclaval.tetris.model.tetromino;

import it.andclaval.tetris.tool.Couple;

public class Tetromino_I extends Tetromino {
	private int state;

	public Tetromino_I(){
		super();
		for (int i=0; i<this.COLUMN; i++){
			this.getShape()[0][i] = this.T_FREE;
			this.getShape()[1][i] = this.T_OCCUPIED;
		}

		this.pivot = new Couple<Integer>(-1, -1);
		this.state = 0; //codifica dello stato iniziale
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}