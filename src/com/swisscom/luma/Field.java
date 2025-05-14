package com.swisscom.luma;

public class Field {
	int xSize;
	int ySize;
	int[][] field;

	public Field(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		field = new int[xSize][ySize];

		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				field[x][y] = 0;
			}
		}
	}

	public Point getFirstField() {
		Point firstField = new Point();
		mainLoop:
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (field[x][y] == 0) {
					firstField.set(x,  y);
					break mainLoop;
				}
			}
		}
		return firstField;
	}
	
	public boolean putPiece(Point origin, Piece piece) {
		for (int i = 0; i < piece.point.length; i ++) {
			if (field[origin.x + piece.getPoint(i).x] < 0 || field[origin.x + piece.getPoint(i).x] >= xSize) {}
			if (field[origin.x + piece.getPoint(i).x][origin.y + piece.getPoint(i).y] != 0) {
				return false;
			}
		}
		return true;
	}
}
