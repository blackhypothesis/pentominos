package com.swisscom.luma;

import java.util.LinkedList;

public class Field {
	int xSize;
	int ySize;
	int[][] field;
	LinkedList<Piece> insertedPieces = new LinkedList<Piece>();

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

	public Field(int xSize, int ySize, Point[] recess) {
		this(xSize, ySize);
		for (int i = 0; i < recess.length; i++) {
			field[recess[i].x][recess[i].y] = 98;
		}
	}

	public int numberOfPieces() {
		return insertedPieces.size();
	}

	public void paint() {
		int paintXSize = xSize + 2;
		int paintYSize = ySize + 2;
		int[][] paintField = new int[paintXSize][paintYSize];
		for (int y = 0; y < paintYSize; y++) {
			paintField[0][y] = 99;
			paintField[paintXSize - 1][y] = 99;
		}
		for (int x = 0; x < paintXSize; x++) {
			paintField[x][0] = 99;
			paintField[x][paintYSize - 1] = 99;
		}

		for (int y = ySize - 1; y >= 0; y--) {
			for (int x = 0; x < xSize; x++) {
				paintField[x + 1][y + 1] = field[x][y];
			}
		}

		for (int y = paintYSize - 2; y >= 0; y--) {
			for (int x = 1; x < paintXSize; x++) {
				if (paintField[x][y + 1] != paintField[x][y]) {
					if (paintField[x - 1][y] == paintField[x][y] && paintField[x - 1][y + 1] == paintField[x][y + 1]) {
						System.out.print("----");
					} else {
						System.out.print("+---");
					}
				} else if (paintField[x - 1][y] != paintField[x][y]) {

					if (paintField[x - 1][y] != paintField[x - 1][y + 1]) {
						System.out.print("+   ");
					} else {
						System.out.print("|   ");
					}
				} else if (paintField[x - 1][y] != paintField[x - 1][y + 1]) {
					System.out.print("+   ");
				}

				else {
					System.out.print("    ");
				}

			}
			System.out.println("");
			for (int x = 1; x < paintXSize; x++) {
				if (paintField[x - 1][y] != paintField[x][y]) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
				if (paintField[x][y] == 0) {
					System.out.print("XX ");
				} else if (paintField[x][y] == 98) {
					System.out.print("OOO");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println("");
		}
	}

	public Point firstEmptyField() {
		Point firstField = new Point();
		mainLoop: for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (field[x][y] == 0) {
					firstField.set(x, y);
					break mainLoop;
				}
			}
		}
		return firstField;
	}

	public boolean pushPiece(Piece piece) {
		return pushPiece(firstEmptyField(), piece);
	}

	public boolean pushPiece(Point origin, Piece piece) {
		piece.setOrigin(origin);
		for (int i = 0; i < piece.size(); i++) {
			if (piece.getPoint(i).x + piece.origin.x < 0 || piece.getPoint(i).x + piece.origin.x >= xSize || piece.getPoint(i).y + piece.origin.y < 0
					|| piece.getPoint(i).y + piece.origin.y >= ySize) {
				return false;
			}
			if (field[piece.getPoint(i).x + piece.origin.x][piece.getPoint(i).y + piece.origin.y] > 0) {
				return false;
			}
		}

		insertedPieces.push(piece.clone());

		for (int i = 0; i < piece.size(); i++) {
			field[piece.getPoint(i).x + piece.origin.x][piece.getPoint(i).y + piece.origin.y] = insertedPieces.size();
		}

		return true;
	}

	public void popPiece() {
		Piece rmPiece = insertedPieces.getFirst();

		for (int i = 0; i < rmPiece.size(); i++) {
			field[rmPiece.getPoint(i).x + rmPiece.origin.x][rmPiece.getPoint(i).y + rmPiece.origin.y] = 0;
		}
		insertedPieces.pop();
	}

	public void printPieces() {
		for (int i = 0; i < insertedPieces.size(); i++) {
			insertedPieces.get(i).print();
		}
	}

	public void paintPieces() {
		for (int i = 0; i < insertedPieces.size(); i++) {
			insertedPieces.get(i).paint();
		}
	}
}
