package models;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

import utils.Utils;

public class MyPolygon extends java.awt.Polygon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283895592393591184L;

	public void draw(Graphics g, boolean connectLast) {
		int prevX = xpoints[0];
		int prevY = ypoints[0];
		for (int i = 1; i < npoints; i++) {
			g.drawLine(prevX, prevY, xpoints[i], ypoints[i]);
			prevX = xpoints[i];
			prevY = ypoints[i];
		}
		if (connectLast) {
			g.drawLine(prevX, prevY, xpoints[0], ypoints[0]);
		}

	}

	public boolean hasIntersectWIthLineWithoutCommonPoint(Line2D line) {
		int prevX = xpoints[0];
		int prevY = ypoints[0];
		Utils utils = new Utils();
		for (int i = 1; i < npoints; i++) {
			Line2D line1 = new Line2D.Float(prevX, prevY, xpoints[i],
					ypoints[i]);
			boolean hasIntersectWithoutCommonPoint = utils
					.hasIntersectWithoutCommonPoint(line1, line);
			if (hasIntersectWithoutCommonPoint) {
				return true;
			}
			prevX = xpoints[i];
			prevY = ypoints[i];
		}
		if (utils.hasIntersectWithoutCommonPoint(line, new Line2D.Float(prevX,
				prevY, xpoints[0], ypoints[0]))) {
			return true;
		}
		return false;

	}

	/**
	 * true if totally contains or just start or end is on vertexes
	 * 
	 * @param line
	 * @return
	 */
	public boolean containsDiameter(Line2D line) {
		double xPlusEpsilon = line.getX1() + (line.getX2() - line.getX1())
				/ 1000;
		double yPlusEpsilon = line.getY1() + (line.getY2() - line.getY1())
				/ 1000;
		return this.contains(xPlusEpsilon, yPlusEpsilon);

	}

	/**
	 * true if is exactly on boundary
	 * 
	 * @param line
	 * @return
	 */
	public boolean isOnBoundary(Line2D line) {
		int index1 = this.getIndexOf(new Point((int)line.getX1(),(int)line.getY1()));
		int index2 = this.getIndexOf(new Point((int)line.getX2(),(int)line.getY2()));
		if(-1==index1||-1==index2){
			return false;
		}else{
			if(Math.abs(index1-index2)<2){
				return true;
			}else{
				return false;
			}
		}
		

	}
	public int getIndexOf(Point p){
		for (int i = 0; i < this.npoints; i++) {
			Point p2 = new Point(xpoints[i], ypoints[i]);
			if(p.equals(p2)){
				return i;
			}
		}
		return -1;
	}

}
