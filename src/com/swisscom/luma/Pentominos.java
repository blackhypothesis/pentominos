package com.swisscom.luma;

import java.util.Arrays;

public class Pentominos {

	public static void main(String[] args) {

		int[] e = { 4, 6, 5, 38, 76 };

		Permutation p = new Permutation(5);
		int c = 1;
		System.out.println(c + "  " + Arrays.toString(p.getElements()));
		while (p.calculateNext()) {
			c++;
			System.out.println(c + "  " + Arrays.toString(p.getElements()));

			if (c == 8) {
				p.calculateNext(1);
				c++;
				System.out.println(c + "  " + Arrays.toString(p.getElements()) + "  !!");
			}

			if (c == 29) {
				p.calculateNext(0);
				c++;
				System.out.println(c + "  " + Arrays.toString(p.getElements()) + "  !!");
			}
		}
		System.out.println("Number of permutations: " + c);
		System.out.println("\n\n\n");

		// Point [] pa = new Point[5];
		// pa = ( [ new Point(0, 0), newPoint(0, 1) ] );

		// XXXXX
		// Point[] pa = { new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(0, 4) };
		
		//   X
		//   X
		// XXX
		// Point[] pa = { new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 2), new Point(2, 2) };
		
		//  X
		// XXX
		//   X
		// Point[] pa = { new Point(2, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) };
		
		//  X
		// XXXX
		Point[] pa = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(1, 1) };
		
		//   X
		//  XX
		// XX
		// Point[] pa = { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2, 2) };

		Shape s = new Shape(pa);
		System.out.println("Shape:");

		System.out.println("==================================================================");
		s.paintVectorShapes();
		System.out.println("==================================================================");
	}
}
