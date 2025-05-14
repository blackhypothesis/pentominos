package com.swisscom.luma;

public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
