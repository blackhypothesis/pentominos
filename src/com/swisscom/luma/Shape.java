package com.swisscom.luma;

import java.util.Vector;

public class Shape {

	Vector<Piece> shape = new Vector<Piece>();
	int currentPiece;
	private Piece nextPiece;

	public Shape(Piece piece) {
		shape.add(piece);

		for (int i = 0; i < 2; i++) {
			for (int r = 0; r < 3; r++) {
				nextPiece = shape.lastElement().clone();
				nextPiece.rotateRight();
				if (newTransformation(nextPiece)) {
					shape.add(nextPiece);
				}
			}
			nextPiece = shape.get(shape.size() - 1).clone();
			nextPiece.flip();
			if (newTransformation(nextPiece)) {
				shape.add(nextPiece);
			}
		}
		currentPiece = -1;
	}
	
	public Piece nextPiece() {
		if (currentPiece + 1 > shape.size() - 1) {
			return null;
		} else {
			currentPiece++;
		}
		return shape.get(currentPiece);
	}

	public void reset() {
		currentPiece = -1;
	}

	public int getTransformations() {
		return shape.size();
	}

	public void paint() {
		for (int v = 0; v < shape.size(); v++) {
			shape.get(v).paint();
		}
	}

	private boolean newTransformation(Piece p) {
		for (int v = 0; v < shape.size(); v++) {
			if (p.compare(shape.get(v))) {
				return false;
			}
		}
		return true;
	}
}
