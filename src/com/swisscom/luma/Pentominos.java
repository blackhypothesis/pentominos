package com.swisscom.luma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pentominos {

	public static void main(String[] args) {

		final int NUMBER_OF_PIECES = 12;

		// Point[] recess = { new Point(3, 3), new Point(4, 3), new Point(4, 4), new Point(3, 4) };
		Point[] recess = { new Point(2, 2), new Point(5, 2), new Point(5, 5), new Point(2, 5) };
		// Point[] recess = { new Point(1, 1), new Point(6, 1), new Point(6, 6), new Point(1, 6) };
		// Point[] recess = { new Point(0, 0), new Point(7, 0), new Point(7, 7), new Point(0, 7) };
		// Point[] recess = { new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5) };
		// Point[] recess = { new Point(0, 0), new Point(0, 5), new Point(10, 0), new Point(10, 5), new Point(5, 2), new Point(5, 3) };

		// Point[] recess = { new Point(0, 7), new Point(0, 8), new Point(3, 7), new Point(3, 8) };
		Field field = new Field(10, 6);
		// Field field = new Field(8, 8, recess);
		// Field field = new Field(8, 8, new Point[] { new Point(0, 0), new Point(7, 0), new Point(7, 7), new Point(0, 7) });
		Permutation perm = new Permutation(NUMBER_OF_PIECES);
		Shape[] shape = new Shape[NUMBER_OF_PIECES];
		Piece[] pi5 = new Piece[NUMBER_OF_PIECES];

		long permCount = 0;
		int solutionCount = 0;
		int piecePush = 0;
		int piecePop = 0;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// ************************************** START Piece Set ****************************************************
		//  X
		// XXX
		//   X
		Point[] p0 = { new Point(2, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) };
		shape[0] = new Shape(new Piece(p0));

		//   X
		//   X
		// XXX
		Point[] p1 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(2, 2) };
		shape[1] = new Shape(new Piece(p1));

		// XXXXX
		Point[] p2 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0) };
		shape[2] = new Shape(new Piece(p2));

		//  X
		// XXXX
		Point[] p3 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(1, 1) };
		shape[3] = new Shape(new Piece(p3));

		//   X
		//  XX
		// XX
		Point[] p4 = { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2, 2) };
		shape[4] = new Shape(new Piece(p4));

		//   XX
		// XXX
		Point[] p5 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(3, 1) };
		shape[5] = new Shape(new Piece(p5));

		//  X
		// XXX
		//  X
		Point[] p6 = { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) };
		shape[6] = new Shape(new Piece(p6));

		// X
		// XXXX
		Point[] p7 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(0, 1) };
		shape[7] = new Shape(new Piece(p7));

		//  XX
		// XXX
		Point[] p8 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(2, 1) };
		shape[8] = new Shape(new Piece(p8));

		// X X
		// XXX
		Point[] p9 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(2, 1) };
		pi5[9] = new Piece(p9);
		shape[9] = new Shape(pi5[9]);

		//  X
		//  X
		// XXX
		Point[] p10 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(1, 2) };
		shape[10] = new Shape(new Piece(p10));

		//  XX
		//  X
		// XX
		Point[] p11 = { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) };
		shape[11] = new Shape(new Piece(p11));
		
		// ************************************** END   Piece Set ****************************************************

		

		for (;;) {
			if (perm.next(perm.getPosition())) {
				permCount++;
				for (;;) {
					Piece currentPiece = shape[perm.getElement()].nextPiece();

					if (currentPiece != null) {
						if (field.pushPiece(currentPiece)) {
							piecePush++;
							perm.incPosition();
							if (perm.getPosition() == perm.elements.length) {
								solutionCount++;
								field.paint();
								System.out.println("Piece[]          = " + Arrays.toString(perm.getElements()));
								System.out.print("Transformation[] = [");
								for (int i = 0; i < perm.elements.length; i ++) {
									System.out.print(shape[perm.elements[i]].currentPiece + ", ");
								}
								System.out.println("]");
								System.out.println("Solution # " + solutionCount + "  Permutation # " + permCount + "   (Piece push: " + piecePush
										+ "  Piece pop: " + piecePop + "  delta: " + (piecePush - piecePop) + ")\n");

								perm.decPosition();
								field.popPiece();
								piecePop++;

								/*
								 * try { String name = reader.readLine(); } catch (IOException e) { e.printStackTrace(); }
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
				if (perm.getPosition() < 0) {
					System.out.println("Number of solutions: " + (solutionCount / 4));
					break;
				}
				field.popPiece();
				piecePop++;
				perm.reset(perm.getPosition());
			}
		}
	}
}
