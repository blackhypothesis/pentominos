package com.swisscom.luma;

import java.util.Vector;

public class Piece {
	public Point[] piece;
	public Point origin;

	public Piece(Point[] piece) {
		this.piece = piece;
		adjust();
		origin = new Point();
	}

	public Point getPoint(int i) {
		return piece[i];
	}

	public Point[] getPoints() {
		return piece;
	}
	
	public int size() {
		return piece.length;
	}

	public boolean compare(Piece p) {
		boolean[] equalPoints = new boolean[size()];
		boolean equalPiece = true;

		if (size() != p.size()) {
			return false;
		}

		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < p.size(); j++) {
				equalPoints[i] = false;

				if (getPoint(i).compare(p.getPoint(j))) {
					equalPoints[i] = true;
					break;
				}
			}
		}

		for (int i = 0; i < size(); i++) {
			if (equalPoints[i] == false) {
				equalPiece = false;
				break;
			}
		}
		return equalPiece;
	}

	public Piece clone() {
		Point[] po = new Point[piece.length];
		for (int i = 0; i < piece.length; i++) {
			po[i] = piece[i].clone();
		}
		Piece p = new Piece(po);
		p.origin = origin.clone();
		return p;
	}

	public void setOrigin(Point origin) {
		this.origin.x = origin.x;
		this.origin.y = origin.y;
	}

	public void rotateRight() {
		for (int i = 0; i < piece.length; i++) {
			int tmp = piece[i].x;
			piece[i].x = piece[i].y;
			piece[i].y = -tmp;
		}
		adjust();
	}

	public void rotateLeft() {
		for (int i = 0; i < piece.length; i++) {
			int tmp = piece[i].y;
			piece[i].y = piece[i].x;
			piece[i].x = -tmp;
		}
		adjust();
	}

	public void flip() {
		for (int i = 0; i < piece.length; i++) {
			piece[i].x = -piece[i].x;
		}
		adjust();
	}

	public Point[] surroundedPoints() {
		Vector<Point> vPoint = new Vector<Point>();

		for (int i = 0; i < piece.length; i++) {
			vPoint.add(new Point(getPoint(i).x, getPoint(i).y + 1));
			vPoint.add(new Point(getPoint(i).x, getPoint(i).y - 1));
			vPoint.add(new Point(getPoint(i).x + 1, getPoint(i).y));
			vPoint.add(new Point(getPoint(i).x - 1, getPoint(i).y));
		}

		for (int i = 0; i < piece.length; i++) {
			for (int j = 0; j < vPoint.size(); j++) {
				if (piece[i].compare(vPoint.get(j))) {
					vPoint.remove(j);
				}
			}
		}

		for (int i = 0; i < vPoint.size(); i++) {
			for (int j = 0; j < vPoint.size(); j++) {
				if (i != j) {
					if (vPoint.get(i).compare(vPoint.get(j))) {
						vPoint.remove(j);
					}
				}
			}
		}
		return (Point[])vPoint.toArray(new Point[vPoint.size()]);
	}

	public void print() {
		String out = new String();
		for (int i = 0; i < piece.length; i++) {
			out = out.concat("p(" + piece[i].x + "," + piece[i].y + ")");
		}
		out = out.concat(" o(" + origin.x + ", " + origin.y + ")");
		System.out.println(out);
	}

	public void paint() {
		boolean paintField[][] = new boolean[piece.length * 2][piece.length * 2];

		for (int i = 0; i < piece.length; i++) {
			paintField[piece[i].x + piece.length][piece[i].y + piece.length] = true;
		}
		for (int y = paintField.length - 1; y >= 0; y--) {
			System.out.println("");
			for (int x = 0; x < paintField.length; x++) {
				if (paintField[x][y]) {
					System.out.print("X");
				} else {
					System.out.print(" ");
				}
			}
		}
		System.out.println("");

	}

	private void adjust() {
		Point minP = getLowerLeft();
		for (int i = 0; i < piece.length; i++) {
			piece[i].x = piece[i].x - minP.x;
			piece[i].y = piece[i].y - minP.y;
		}
	}

	private Point getLowerLeft() {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;

		for (int i = 0; i < piece.length; i++) {
			if (piece[i].y < minY) {
				minY = piece[i].y;
			}
		}
		for (int i = 0; i < piece.length; i++) {
			if (piece[i].y == minY) {
				if (piece[i].x < minX) {
					minX = piece[i].x;
				}
			}
		}
		return new Point(minX, minY);
	}
}
