package it.andclaval.tetris.render;

import it.andclaval.tetris.model.Matrix;

import java.util.Scanner;

public class TextRendering {
	
	public void rendering(Matrix m_class){
		int[][] matrix = m_class.getMatrix();
		//scrivo gli indici delle colonne
		System.out.println("Lv: " + m_class.getLevel() + " Righe per lv up: " + m_class.getRowsToLevelUp());
		System.out.println("Score: " + m_class.getScore());
		System.out.println("Combo: #linee = " + m_class.getCombo() + " #hit = " + m_class.getComboInfo()[1]);
		
		int i;
		System.out.print("\t");
		for (i=0; i<matrix[0].length; i++){
			System.out.print(i + "\t");
		}
		System.out.println();
		
		i=0;
		for (int r=0; r<matrix.length; r++){
			System.out.print(i + "\t");
			for (int c=0; c<matrix[0].length; c++){
				System.out.print(matrix[r][c] + "\t");
			}
			System.out.println();
			i++;
		}
		
		System.out.println();
		System.out.println();
	}
	
	public String input(){
		Scanner scannerDiLinee = new Scanner(System.in);	
		return scannerDiLinee.nextLine();
	}
	

	
}
