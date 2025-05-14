package com.swisscom.luma;

import java.util.Vector;

public class Shape {
	private Point[] shape;
	private Vector<Point[]> vShape = new Vector<Point[]>();

	public Shape(Point[] shape) {
		this.shape = shape;
		adjust();
		addShape();
		while (next())
			;
	}

	public Point[] getShape() {
		return shape;
	}

	public Piece getPiece() {
		return new Piece(shape);
	}

	public void rotateRight() {
		for (int i = 0; i < shape.length; i++) {
			int tmp = shape[i].x;
			shape[i].x = shape[i].y;
			shape[i].y = -tmp;
		}
		adjust();
	}

	public void flip() {
		for (int i = 0; i < shape.length; i++) {
			shape[i].y = -shape[i].y;
		}
		adjust();
	}

	public boolean next() {
		flip();
		System.out.println(">> flip");
		if (newTransformation()) {
			addShape();
			return true;
		} else {
			rotateRight();
			System.out.println(">> rotate right");
			if (newTransformation()) {
				addShape();
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean newTransformation() {
		boolean[] equal = new boolean[shape.length];
		boolean[] vEqual = new boolean[vShape.size()];
		boolean shapeEqual;
		boolean newTrans = true;

		for (int v = 0; v < vShape.size(); v++) {
			shapeEqual = true;
			for (int i = 0; i < shape.length; i++) {
				equal[i] = false;
				for (int j = 0; j < shape.length; j++) {
					if (shape[i].x == vShape.get(v)[j].x && shape[i].y == vShape.get(v)[j].y) {
						equal[i] = true;
						break;
					}
				}
			}
			for (int i = 0; i < shape.length; i++) {
				if (!equal[i]) {
					shapeEqual = false;
					break;
				}
			}
			vEqual[v] = shapeEqual;
		}
		for (int v = 0; v < vShape.size(); v++) {
			if (vEqual[v]) {
				newTrans = false;
				break;
			}
		}
		return newTrans;
	}

	public void print() {
		String out = new String();
		for (int i = 0; i < shape.length; i++) {
			out = out.concat(i + "[" + shape[i].x + " " + shape[i].y + "] ");
		}
		System.out.println(out);
	}

	public void printVectorShapes() {
		String out = new String();
		for (int v = 0; v < vShape.size(); v++) {
			out = out.concat("v(" + v + ")(");
			for (int i = 0; i < vShape.get(v).length; i++) {
				out = out.concat(i + "[" + vShape.get(v)[i].x + " " + vShape.get(v)[i].y + "] ");
			}
			out = out.concat(")   ");
		}
		System.out.println(out);
	}

	public void paint() {
		boolean paintField[][] = new boolean[shape.length * 2][shape.length * 2];

		for (int i = 0; i < shape.length; i++) {
			paintField[shape[i].x + shape.length][shape[i].y + shape.length] = true;
		}
		for (int x = 0; x < paintField.length; x++) {
			System.out.println("");
			for (int y = 0; y < paintField.length; y++) {
				if (paintField[x][y]) {
					System.out.print("X");
				} else {
					System.out.print(" ");
				}
			}
		}
		System.out.println("");
	}

	public void paintVectorShapes() {
		for (int v = 0; v < vShape.size(); v++) {
			boolean paintField[][] = new boolean[vShape.get(v).length * 2][vShape.get(v).length * 2];
			for (int i = 0; i < vShape.get(v).length; i++) {
				paintField[vShape.get(v)[i].x + vShape.get(v).length][vShape.get(v)[i].y + vShape.get(v).length] = true;
			}
			for (int x = 0; x < paintField.length; x++) {
				System.out.println("");
				for (int y = 0; y < paintField.length; y++) {
					if (paintField[x][y]) {
						System.out.print("X");
					} else {
						System.out.print(" ");
					}
				}
			}
			System.out.println("");
		}
	}

	private void adjust() {
		Point minP = getLowerLeft();
		for (int i = 0; i < shape.length; i++) {
			shape[i].x = shape[i].x - minP.x;
			shape[i].y = shape[i].y - minP.y;
		}
	}

	private Point getLowerLeft() {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;

		for (int i = 0; i < shape.length; i++) {
			if (shape[i].y < minY) {
				minY = shape[i].y;
			}
		}
		for (int i = 0; i < shape.length; i++) {
			if (shape[i].y == minY) {
				if (shape[i].x < minX) {
					minX = shape[i].x;
				}
			}
		}
		return new Point(minX, minY);
	}

	private void addShape() {
		Point[] tmp = new Point[shape.length];
		for (int i = 0; i < shape.length; i++) {
			tmp[i] = new Point(shape[i].x, shape[i].y);

		}
		vShape.add(tmp);
	}
}
