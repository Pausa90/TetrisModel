package it.andclaval.tetris.model;

import static org.junit.Assert.*;
import it.andclaval.tetris.model.tetromino.*;
import it.andclaval.tetris.tool.Couple;

import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
	private Matrix matrix;
	private TetrisGame tetrisGame;
	private String[] nomiTetromini = {"I","J","L","O","S","T","Z"};

	@Before
	public void setUp() throws Exception {
		this.tetrisGame = new TetrisGame();
		this.matrix = new Matrix(this.tetrisGame);
	}

	@Test
	public void testMatrix() {
		assertEquals(this.matrix.getGame(),this.tetrisGame);
		assertEquals(this.matrix.getROW(),20);
		assertEquals(this.matrix.getCOLUMN(),12);
		assertEquals(this.matrix.getCoordinates().length,4);
		Integer menoUno = Integer.valueOf(-1); 
		for(Couple<Integer> c : this.matrix.getCoordinates()){
			assertEquals(c.getFirst(),menoUno);
			assertEquals(c.getSecond(),menoUno);
		}
	}

	@Test
	public void testPutTetromino() {
		for(int i=0; i<this.nomiTetromini.length;i++){
			try {
				Class<Tetromino> classeTetromino = (Class<Tetromino>) Class.forName("it.andclaval.tetris.model.tetromino.Tetromino_"+ this.nomiTetromini[i]);
				Tetromino tetromino = (Tetromino)classeTetromino.newInstance();
				//Inserisco il tetromino in una matrice vuota
				assertTrue(testPutTetromino_FreeMatrix_Parameter(tetromino));
				setInitialCondition();
				//Inserisco il tetromino in una matrice piena
				assertTrue(testPutTetromino_FullMatrix_Parameter(tetromino));
				//Ripristino i valori iniziali per il prossimo tetromino
				setInitialCondition();
			} catch (ClassNotFoundException e) {
				System.out.println("testPutTetromino: Classe Tetromino_" + this.nomiTetromini[i]+ " non esistente");
			} catch (InstantiationException e) {
				System.out.println("testPutTetromino: errore di instanziazione classe Tetromino_" + this.nomiTetromini[i]);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("testPutTetromino: accesso illegale classe Tetromino_" + this.nomiTetromini[i]);
				e.printStackTrace();
			}

		}
	}

	/*
	 * Metodo che inserisce un tetromino passato per parametro in una matrice vuota
	 * Viene richiamato da testPutTetromino 
	 */ 
	private boolean testPutTetromino_FreeMatrix_Parameter(Tetromino tetromino){
		boolean result = true;

		//Test inserimento Tetromino parametrico in una matrice vuota
		int numCols = this.matrix.getMatrix()[0].length; //numero di colonne
		//Verifico che inizialmente le prime due righe della matrice siano libere
		for(int i=0; i<2; i++){
			for(int j=0; j<numCols; j++)
				result &= this.matrix.getMatrix()[i][j] == this.matrix.getFREE(); 
		}
		//Inserisco il tetromino passato per parametro
		this.matrix.putTetromino(tetromino);
		//Verifico che la prima e la seconda riga per le prime 4 colonne siano libere
		for(int i=0; i<2; i++){
			for(int j=0; j<4; j++){
				result &= this.matrix.getMatrix()[i][j]==this.matrix.getFREE();
			}
		}

		//Verifico che la prima e la seconda riga per le colonne da 4 a 8 siano occupate con il tetromino
		for(int i=0; i<2; i++){
			for(int j=4; j<8; j++){
				result &= this.matrix.getMatrix()[i][j]==tetromino.getShape()[i][j-4];
			}
		}
		//Verifico che la prima e la seconda riga per le colonne da 8 in poi siano libere
		for(int i=0; i<2; i++){
			for(int j=8; j<numCols; j++){
				result &= this.matrix.getMatrix()[i][j] == this.matrix.getFREE(); 
			}
		}	
		return result;
	}

	/*
	 * Metodo che inserisce un tetromino passato per parametro in una matrice completamente piena
	 * Viene richiamato da testPutTetromino 
	 */ 
	private  boolean testPutTetromino_FullMatrix_Parameter(Tetromino tetromino){
		//Test inserimento tetromino generico in una matrice piena
		boolean result = true;
		//Il gioco non è finito
		result &= (!this.matrix.getGame().isEnd());
		int numCols = this.matrix.getMatrix()[0].length; //numero di colonne
		int numRows = this.matrix.getMatrix().length; //numero di righe

		//Riempio la matrice dal basso verso l'alto
		for(int i=numRows-1; i>=0; i--){
			for(int j=0; j<numCols; j++){
				this.matrix.getMatrix()[i][j] = this.matrix.getOCCUPIED(); 
				result &= this.matrix.getMatrix()[i][j] == this.matrix.getOCCUPIED(); 
			}
		}
		//Provo ad inserire il tetromino
		this.matrix.putTetromino(tetromino);
		//Il gioco dovrebbe essere finito
		result &= this.matrix.getGame().isEnd();
		//La matrice dovrebbe essere rimasta invariata
		for(int i=0; i<2; i++){
			for(int j=0;j<numCols;j++){
				result &= this.matrix.getMatrix()[i][j] == this.matrix.getOCCUPIED();
			}
		}
		return result;
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotateCurrent() {
		fail("Not yet implemented");
	}

	@Test
	public void testTraslateCurrent() {
		fail("Not yet implemented");
	}


	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/*
	 * Ripristina le condizioni iniziali del gioco: la matrice viene messa tutta free e il gioco non è finito 
	 */
	private void setInitialCondition(){
		int numCols = this.matrix.getMatrix()[0].length; //numero di colonne
		int numRows = this.matrix.getMatrix().length; //numero di righe

		for(int i=0;i<numRows;i++){
			for(int j=0;j<numCols;j++){
				this.matrix.getMatrix()[i][j] = this.matrix.getFREE();
			}
		}
		this.matrix.getGame().setEnd(false);
	}
}

