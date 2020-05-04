package com.bradenlittle.art.computerlines;

import java.awt.Point;

public class Step {
	int x, y;
	public Step(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point pointAfter(int x, int y) {
		return new Point(this.x + x, this.y + y);
	}
	public Point pointAfter(Point p) {
		return pointAfter(p.x, p.y);
	}
	public boolean mutate() {
		if (Math.random() > .32) {
			return true;
		}
		return false;
	}
	public Step reset() {
		return new Step(0, this.y);
		
	}
	public static Step[] split(Step s) {
		Step[] steps = new Step[2];
		steps[0] = new Step(-s.y, s.y);
		steps[1] = new Step(s.y, s.y);
		return steps;
	}
}
