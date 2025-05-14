package com.swisscom.luma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test02 {

	public static void main(String[] args) {

		final int NUMBER_OF_PIECES = 4;

		Field field = new Field(4, 3);
		int[] pa = new int[] { 6, 5, 4, 0, 3, 1, 2 };
		//Permutation perm = new Permutation(pa);
		Permutation perm = new Permutation(NUMBER_OF_PIECES);
		Shape[] shape = new Shape[NUMBER_OF_PIECES];

		int permCount = 0;
		int solutionCount = 0;
		int piecePush = 0;
		int piecePop = 0;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// ************************************** START Piece Set ****************************************************
		//  X
		// XXX
		Point[] p0 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1) };
		shape[0] = new Shape(new Piece(p0));

		//  X
		// XX
		Point[] p1 = { new Point(0, 0), new Point(1, 0), new Point(1, 1) };
		shape[1] = new Shape(new Piece(p1));

		// XXX
		Point[] p2 = { new Point(0, 0), new Point(1, 0), new Point(2, 0) };
		shape[2] = new Shape(new Piece(p2));

		// XX
		Point[] p3 = { new Point(0, 0), new Point(1, 0) };
		shape[3] = new Shape(new Piece(p3));

		// ************************************** END   Piece Set ****************************************************

		/*
		int[] tr = new int[] { 1, 3, 0, 2, 2, 6, 0 };
		for (int i = 0; i < NUMBER_OF_PIECES; i++) {
			shape[i].currentPiece = tr[i];
		}
		*/

		for (;;) {
			if (perm.next(perm.getPosition())) {
				
				/*
				// ******************************************************************************************************
				System.out.println("position = " + perm.getPosition());
				System.out.println("Piece[]          = " + Arrays.toString(perm.getElements()));
				System.out.print("Transformation[] = [");
				for (int i = 0; i < perm.elements.length; i++) {
					System.out.print(shape[perm.elements[i]].currentPiece + ", ");
				}
				System.out.println("]");
				System.out.println("Solution # " + solutionCount + "  Permutation # " + permCount + "   (Piece push: " + piecePush
						+ "  Piece pop: " + piecePop + "  delta: " + (piecePush - piecePop) + ")\n");
				// ******************************************************************************************************
				*/
				
				permCount++;
				for (;;) {
					Piece currentPiece = shape[perm.getElement()].nextPiece();

					if (currentPiece != null) {
						if (field.pushPiece(currentPiece)) {
							piecePush++;

							/*
							// ******************************************************************************************************
							field.paint2();
							System.out.println("position = " + perm.getPosition() + "   push");
							System.out.println("Piece[]          = " + Arrays.toString(perm.getElements()));
							System.out.print("Transformation[] = [");
							for (int i = 0; i < perm.elements.length; i++) {
								System.out.print(shape[perm.elements[i]].currentPiece + ", ");
							}
							System.out.println("]");
							System.out.println("Solution # " + solutionCount + "  Permutation # " + permCount + "   (Piece push: " + piecePush
									+ "  Piece pop: " + piecePop + "  delta: " + (piecePush - piecePop) + ")\n");
							// ******************************************************************************************************
							*/
							
							perm.incPosition();
							if (perm.getPosition() == perm.elements.length) {
								solutionCount++;

								// ******************************************************************************************************
								field.paint();
								System.out.println("position = " + perm.getPosition() + "   pop");
								System.out.println("Piece[]          = " + Arrays.toString(perm.getElements()));
								System.out.print("Transformation[] = [");
								for (int i = 0; i < perm.elements.length; i++) {
									System.out.print(shape[perm.elements[i]].currentPiece + ", ");
								}
								System.out.println("]");
								System.out.println("Solution # " + solutionCount + "  Permutation # " + permCount + "   (Piece push: " + piecePush
										+ "  Piece pop: " + piecePop + "  delta: " + (piecePush - piecePop) + ")\n");
								// ******************************************************************************************************

								perm.decPosition();
								field.popPiece();
								piecePop++;

								/*
								// ******************************************************************************************************
								field.paint2();
								System.out.println("Piece[]          = " + Arrays.toString(perm.getElements()));
								System.out.print("Transformation[] = [");
								for (int i = 0; i < perm.elements.length; i++) {
									System.out.print(shape[perm.elements[i]].currentPiece + ", ");
								}
								System.out.println("]");
								System.out.println("Solution # " + solutionCount + "  Permutation # " + permCount + "   (Piece push: " + piecePush
										+ "  Piece pop: " + piecePop + "  delta: " + (piecePush - piecePop) + ")\n");
								// ******************************************************************************************************
								*/
								
								/*
								try {
									String name = reader.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
								*/

								continue;
							}
						}
					} else {
						shape[perm.getElement()].reset();
						break;
					}
				}
			} else {
				perm.decPosition();
				field.popPiece();
				piecePop++;
				
				/*
				// ******************************************************************************************************
				field.paint2();
				System.out.println("position = " + perm.getPosition() + "   pop");
				System.out.println("Piece[]          = " + Arrays.toString(perm.getElements()));
				System.out.print("Transformation[] = [");
				for (int i = 0; i < perm.elements.length; i++) {
					System.out.print(shape[perm.elements[i]].currentPiece + ", ");
				}
				System.out.println("]");
				System.out.println("Solution # " + solutionCount + "  Permutation # " + permCount + "   (Piece push: " + piecePush + "  Piece pop: " + piecePop
						+ "  delta: " + (piecePush - piecePop) + ")\n");
				// ******************************************************************************************************
				*/
				
				perm.reset(perm.getPosition());
			}
		}
	}
}
