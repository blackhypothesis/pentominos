package com.swisscom.luma;

public class Test01 {

	public static void main(String[] args) {

		//   X
		//   X
		// XXX
		Point[] p0 = { new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(2, 2) };
		Piece piece = new Piece(p0);

		Point[] sPoint = piece.surroundedPoints();

		piece.paint();

		Piece sPiece = new Piece(sPoint);
		for (int i = 0; i < 1; i++) {
			sPoint = sPiece.surroundedPoints();
			sPiece = new Piece(sPoint);
			sPiece.paint();
		}
		for (Point i : sPoint) {
			i.print();
		}
	}
}