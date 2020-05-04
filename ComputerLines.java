package com.bradenlittle.art.computerlines;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;

public class ComputerLines extends JFrame{
	DynamicArray<Point> points = new DynamicArray<Point>(Point.class);
	HashMap<Point, Point> map = new HashMap<Point, Point>();
	Canvas2 c = new Canvas2();
	public static void main(String[] args) {
		ComputerLines cLines = new ComputerLines();
	}
	public ComputerLines() {
		
		c.setVisible(true);
		c.setSize(this.getSize());
		c.setBackground(Color.black);
		this.add(c);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.transferFocus();
		c.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == e.VK_ESCAPE) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				c.wipe();
				drawLines();
				c.setPoints(map);
				c.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}
	public void drawLines() {
		Random r = new Random();
		for (int i = 0; i < this.getWidth(); i += 50) {
			c.setPoints(recDraw(this.getHeight() , i, new Step(0 , -100), new Point(i, this.getHeight()), new HashMap<Point, Point>()));	
			c.repaint();
		}
	}
	private HashMap<Point, Point> recDraw(int level, int sway, Step s, Point last, HashMap<Point, Point> map) {
		if (level < 0) return map;

		if (s.mutate()) {
			Step[] arr = Step.split(s);
			Point p1 = arr[0].pointAfter(last);
			Point p2 = arr[1].pointAfter(last);
			map.put(p1, last);
			map.put(p2, last);
			System.out.print("\nSplit... ");
			recDraw(level + arr[0].y, sway + arr[0].x, arr[0].reset(), p1, map);
			System.out.print("done 1 ");
			recDraw(level + arr[1].y, sway + arr[1].x, arr[1].reset(), p2, map);
			System.out.print("done 2");
		} else {
			map.put( s.pointAfter(last), last);
			recDraw(level + s.y, sway + s.x, s, s.pointAfter(last), map);
		}
		return map;
	}
	
}
