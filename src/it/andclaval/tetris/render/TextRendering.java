package it.andclaval.tetris.render;

import java.util.Scanner;

public class TextRendering {
	
	public void rendering(int[][] matrix){

		//scrivo gli indici delle colonne
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
