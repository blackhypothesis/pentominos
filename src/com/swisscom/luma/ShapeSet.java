package com.swisscom.luma;

import java.util.Vector;

public class ShapeSet {
	
	private Vector<Shape> vShape = new Vector<Shape>();
	private Vector<Point> vPoint = new Vector<Point>();
	
	public ShapeSet(int size) {
		
		Point[] point = new Point[1];
		point[0] = new Point();
		Piece piece = new Piece(point);
		
		Point[] surroundedPoint = piece.surroundedPoints();
		
		for (Point sPoint : surroundedPoint) {
			Point[] p = new Point[1];
			Piece piece = new Piece()
			
		}
	}
	
}
