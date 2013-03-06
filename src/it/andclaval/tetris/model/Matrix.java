package it.andclaval.tetris.model;

import java.util.zip.CheckedOutputStream;

import it.andclaval.tetris.model.tetromino.Tetromino;
import it.andclaval.tetris.tool.Couple;

public class Matrix {
	private TetrisGame game;
	//Dimensioni matrice

	/*private final int*/
	private final int ROW = 20;
	private final int COLUMN = 12;
	private int[][] matrix;
	//Macro per la matrice
	private final int FREE = 0;
	private final int OCCUPIED = 1;
	private final int CURRENT = 2;
	//Coordinate del pezzo
	private Couple<Integer>[] coordinates;

	private String currentTetromino;
	private int pivotTetromino;

	public Matrix(TetrisGame game){
		this.game = game;
		this.setMatrix(new int[ROW][COLUMN]);
		this.coordinates = new Couple[4];
		for (int i=0; i<this.coordinates.length; i++)
			this.coordinates[i] = new Couple<Integer>(-1, -1);
		this.pivotTetromino = -1;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public TetrisGame getGame() {
		return game;
	}

	public void setGame(TetrisGame game) {
		this.game = game;
	}

	public int getROW() {
		return ROW;
	}

	public int getCOLUMN() {
		return COLUMN;
	}

	public int getFREE() {
		return FREE;
	}

	public int getOCCUPIED() {
		return OCCUPIED;
	}

	public int getCURRENT() {
		return CURRENT;
	}

	public Couple<Integer>[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Couple<Integer>[] coordinates) {
		this.coordinates = coordinates;
	}

	/** Inserisce un nuovo tetromino nella matrice **/
	public void putTetromino(Tetromino next){

		this.setInitialCoordinates(next);
		if (!this.isFreeToInsertBelow())
			this.game.setEnd(true);
		else
			putTetrominoIntoMatrix();
	}

	private void putTetrominoIntoMatrix() {
		for (Couple<Integer> couple : this.coordinates){
			this.matrix[couple.getFirst()][couple.getSecond()] = this.CURRENT;
		}
	}

	private void setInitialCoordinates(Tetromino next) {
		int beginCell = 4;
		int[][] shape = next.getShape();
		int pCoord = 0; //puntatore all'array di coordinate
		Couple<Integer> pivot = next.getPivot();
		int pivot_row = pivot.getFirst();
		int pivot_column = pivot.getSecond();

		for (int i=0; i<shape[0].length; i++){
			if (shape[0][i] == this.CURRENT){
				this.coordinates[pCoord].setCouple(0,beginCell+i);
				if (i == pivot_column && pivot_row == 0)
					this.pivotTetromino = pCoord;
				pCoord++;
			}
			if (shape[1][i] == this.CURRENT){
				this.coordinates[pCoord].setCouple(1,beginCell+i);
				if (i == pivot_column && pivot_row == 1)
					this.pivotTetromino = pCoord;
				pCoord++;
			}

		}
		this.currentTetromino = next.getClass().getName();
	}

	/** Controllo se la posizione è libera per inserire/muovere il tetromino **/
	private boolean isFreeToInsertBelow() {
		for (Couple<Integer> couple : this.coordinates)
			if (couple.getFirst()+1>=20 || this.matrix[couple.getFirst()+1][couple.getSecond()] == this.OCCUPIED)
				return false;
		return true;
	}

	/** Aggiornamento della matrice allo scattare del quanto di tempo **/
	public void update(){
		if (this.isFreeToInsertBelow()){
			this.writeStatus(this.FREE);
			this.updateCoordinatesBelow();
			this.writeStatus(this.CURRENT);
		}
		else{
			this.writeStatus(this.OCCUPIED);
			this.cleanRows();
			this.game.startNextTetromino();
		}
	}

	/** Controlla se una o più righe si possono svuotare (e le cancella) **/
	private void cleanRows() {
		/***********************************************************************************************************************************/
	}

	/** Incrementa la riga di ogni coordinata di 1 **/
	private void updateCoordinatesBelow() {
		for (Couple<Integer> couple : this.coordinates)
			couple.setFirst(couple.getFirst()+1);
	}

	/** Incrementa la colonna di ogni coordinata di 1 **/
	private void updateCoordinatesSide(boolean toLeft) {
		for (Couple<Integer> couple : this.coordinates)
			if (toLeft)
				couple.setSecond(couple.getSecond()-1);
			else
				couple.setSecond(couple.getSecond()+1);
	}

	/** Scrive lo stato su ogni cordinata **/
	private void writeStatus(int status){
		for (Couple<Integer> coord : this.coordinates)
			this.matrix[coord.getFirst()][coord.getSecond()] = status;	
	}

	/************************************** TODO ottimizzabile con solo il pivot (senza fromTo) ***********************/
	/** Aggiornamento della matrice al ruotare del pezzo **/
	public void rotateCurrent(boolean clockWise){
		Couple<Couple<Integer>> fromTo = this.getMinMaxCoordinates(); //Primo elemento coppia di righe, secondo coppia di colonne

		if (this.currentTetromino.equals("Tetromino_O"))
			return;


		int[][] subMatrix = this.fromPositionToSubMatrix();  
		if (clockWise)
			subMatrix = this.rotateClockWise(subMatrix);
		else
			subMatrix = this.rotateAntiClockWise(subMatrix);

		if (this.isFreeToInsertAfterRotation(subMatrix, fromTo)){
			this.writeStatus(this.FREE);
			this.updateCoordinatesAfterRotation(subMatrix);
			this.writeStatus(this.CURRENT);
		}		
	}

	private void updateCoordinatesAfterRotation(int[][] subMatrix) {
		int startRow;
		int startCol;
		int pivot_r;
		int pivot_c;

		if (this.currentTetromino.equals("Tetromino_I")){ /**************************** TODO ************************/
			startRow = 4;
			startCol = 4;
			pivot_r = 0;
			pivot_c = 0;
		}
		else{
			startRow = this.coordinates[this.pivotTetromino].getFirst()-1;
			startCol = this.coordinates[this.pivotTetromino].getSecond()-1;
			pivot_r = 1;
			pivot_c = 1;
		}
		int i = 0;

			
		for (int r = 0; r < subMatrix.length; r++){
			for (int c = 0; c < subMatrix[0].length; c++){

				if (subMatrix[r][c] == this.CURRENT){
					if (r==pivot_r && c==pivot_c)
						this.pivotTetromino = i;
					this.coordinates[i].setCouple(r+startRow, c+startCol);
					i++;
				}
			}
		}
	}

	/** TODO: togliere fromTo e usare il pivot **/
	private boolean isFreeToInsertAfterRotation(int[][] subMatrix, Couple<Couple<Integer>> fromTo) {
		Couple<Integer> rows = fromTo.getFirst();
		Couple<Integer> columns = fromTo.getSecond();

		for (int r = rows.getFirst(); r < subMatrix.length; r++){
			for (int c = columns.getFirst(); c < subMatrix[0].length; c++){

				if (this.matrix[r][c] == this.OCCUPIED)
					return false;
			}
		}
		return true;
	}

	private Couple<Couple<Integer>> getMinMaxCoordinates() {
		int minRow = 100;
		int maxRow = 0;
		int minColumn = 100;
		int maxColumn = 0;

		//Preleviamo la riga minima/massima e la colonna minima/massima
		for (Couple<Integer> c : this.coordinates){
			if (c.getFirst() < minRow)
				minRow = c.getFirst();
			else if (c.getFirst() > maxRow)
				maxRow = c.getFirst();

			if (c.getSecond() < minColumn)
				minColumn = c.getSecond();
			else if (c.getSecond() > maxColumn)
				maxColumn = c.getSecond();
		}
		Couple<Integer> rows = new Couple<Integer>(minRow, maxRow);
		Couple<Integer> columns = new Couple<Integer>(minColumn, maxColumn);	
		Couple<Couple<Integer>> couple = new Couple<Couple<Integer>>(rows, columns);

		return couple;
	}

	private int[][] fromPositionToSubMatrix() {

		int matrixDim;
		int startRow;
		int startCol;

		if (this.currentTetromino.equals("Tetromino_I")){ /**************************** TODO ************************/
			matrixDim = 4;
			startRow = 4;
			startCol = 4;
		}
		else{
			matrixDim = 3;
			startRow = this.coordinates[this.pivotTetromino].getFirst()-1;
			startCol = this.coordinates[this.pivotTetromino].getSecond()-1;
		}

		int[][] subMatrix = new int[matrixDim][matrixDim];

		for (int r = startRow; r < startRow+3; r++){
			for (int c = startCol; c < startCol+3; c++){
				if (this.matrix[r][c]==this.CURRENT)
					subMatrix[r-startRow][c-startCol] = this.matrix[r][c];
			}
		}
		return subMatrix;
	}

	private int[][] rotateAntiClockWise(int[][] subMatrix) {
		int numRows = subMatrix.length;
		int numColumns = subMatrix[0].length;
		int[][] output = new int[subMatrix[0].length][subMatrix.length];
		for(int j=0; j<numRows; j++){
			for(int i=0; i<numColumns; i++){
				output[i][j] = subMatrix[j][numColumns-i-1];
			}
		}
		return output;
	}

	private int[][] rotateClockWise(int[][] subMatrix){
		int numRows = subMatrix.length;
		int numColumns = subMatrix[0].length;
		int[][] output = new int[subMatrix[0].length][subMatrix.length];
		for(int j=0; j<numRows; j++){
			for(int i=0; i<numColumns; i++){
				output[i][j] = subMatrix[numRows-j-1][i];
			}
		}
		return output;
	}

	/** Aggiornamento della matrice al traslare del pezzo
	 * 	true se ha traslato, false se è al bordo **/
	public boolean traslateCurrent(boolean toLeft){
		if (this.isFreeToInsertSide(toLeft)){
			this.writeStatus(this.FREE);
			this.updateCoordinatesSide(toLeft);
			this.writeStatus(this.CURRENT);
			return true;
		}
		return false;
	}

	/** Controllo se la posizione è libera per muovere il tetromino **/
	private boolean isFreeToInsertSide(boolean toLeft) {
		for (Couple<Integer> couple : this.coordinates)
			if (toLeft){
				if (couple.getSecond()-1<0 || this.matrix[couple.getFirst()][couple.getSecond()-1] == this.OCCUPIED)
					return false;
			}
			else
				if (couple.getSecond()+1>11 || this.matrix[couple.getFirst()][couple.getSecond()+1] == this.OCCUPIED)
					return false;
		return true;
	}

	/* 
	 * Due matrici sono uguali se hanno lo stesso numero di righe e colonne e se 
	 * il loro contenuto è lo stesso cella per cella.
	 */
	public boolean equals(Object o){
		Matrix matrix = (Matrix) o;
		boolean result = this.ROW == matrix.getROW() &&
				this.COLUMN == matrix.getCOLUMN();
		int[][] matrixValues = matrix.getMatrix();
		for(int i=0; i<this.ROW && result;i++){
			for(int j=0; j<this.COLUMN && result; j++){
				if(this.matrix[i][j]!=matrixValues[i][j]){
					result = false;
				}	
			}
		}
		return result;
	}
}
