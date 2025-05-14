package com.swisscom.luma;

public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public boolean compare(Point p) {
		if (x == p.x && y == p.y) {
			return true;
		} else {
			return false;
		}
	}

	public Point clone() {
		return new Point(this.x, this.y);
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void print() {
		System.out.println("[" + x + ", " + y + "]");
	}
}
