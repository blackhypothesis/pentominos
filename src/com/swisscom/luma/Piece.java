package com.swisscom.luma;

public class Piece {
	public Point[] point;

	public Piece(Point[] point) {
		this.point = point;
	}
	
	public Point getPoint(int i) {
		return point[i];
	}
}

