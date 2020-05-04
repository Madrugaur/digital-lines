package com.bradenlittle.art.computerlines;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Map;
import java.util.Random;

public class Canvas2 extends Canvas {

	Map<Point, Point> map;
	public Canvas2() {
	}
	public void setPoints(Map<Point, Point> map) {
		if (this.map == null) this.map = map;
		else this.map.putAll(map);

	}
	public void wipe() {
		map = null;
	}
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.setColor(Color.black);
		((Graphics2D) g).setStroke(new BasicStroke(2));
		if (map == null) return;
		for (Point p1 : map.keySet()) {
			Point p2 = map.get(p1);
			g.setColor(randomColor());
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			g.drawOval(p2.x - 5, p2.y - 5 , 10, 10);
		}
	}
	private Color randomColor() {
		Random r = new Random();
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}
}
