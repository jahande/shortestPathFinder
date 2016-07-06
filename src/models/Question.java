package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Question {
	private ArrayList<Point> allpoints = new ArrayList<Point>();
	private int starIndex = -1;
	private int endIndex = -1;
	private boolean outterFinalized = false;
	private boolean innerFinalized = false;
	private MyPolygon outterPolygon = new MyPolygon();
	private List<MyPolygon> innerPolygons = new ArrayList<MyPolygon>();
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	private ArrayList<Vertex> shortestPath = new ArrayList<Vertex>();

	public MyPolygon getOutterPolygon() {
		return outterPolygon;
	}

	public void setOutterPolygon(MyPolygon outterPolygon) {
		this.outterPolygon = outterPolygon;
	}

	public List<MyPolygon> getInnerPolygons() {
		return innerPolygons;
	}

	public void setInnerPolygons(List<MyPolygon> innerPolygons) {
		this.innerPolygons = innerPolygons;
	}

	public Point getStartPoint() {

		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		this.starIndex = this.allpoints.size();
		this.allpoints.add(startPoint);
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void addToOutterPolygon(int x, int y) {
		outterPolygon.addPoint(x, y);
		this.allpoints.add(new Point(x, y));
	}

	public void addToLastInnerPolygon(int x, int y) {
		getLastInnerPolygon().addPoint(x, y);
		this.allpoints.add(new Point(x, y));
	}

	private MyPolygon getLastInnerPolygon() {
		int lastIndex = this.innerPolygons.size() - 1;
		return this.innerPolygons.get(lastIndex);
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		this.endIndex = this.allpoints.size();
		this.allpoints.add(endPoint);
	}

	public void draw(Graphics g) {
		// g.drawLine(0, 0, (int)(Math.random()*100), (int)(Math.random()*100));
		g.setColor(Color.BLACK);
		outterPolygon.draw(g, outterFinalized);
		g.setColor(Color.RED);
		for (int i = 0; i < this.innerPolygons.size(); i++) {
			if ((this.innerPolygons.size() - 1) == i) {
				innerPolygons.get(i).draw(g, innerFinalized);
			} else {
				innerPolygons.get(i).draw(g, true);
			}
		}
		g.setColor(Color.BLUE);
		g.drawLine(this.startPoint.x, startPoint.y, startPoint.x, startPoint.y);
		g.drawLine(this.endPoint.x, endPoint.y, endPoint.x, endPoint.y);
		drawPath(g);
	}

	public void drawPath(Graphics g) {
		if (shortestPath.size() > 1) {
			g.setColor(Color.GREEN);
			Vertex prev = shortestPath.get(0);
			Vertex temp;
			for (int i = 1; i < shortestPath.size(); i++) {
				temp = shortestPath.get(i);
				g.drawLine(prev.getCoordination().x, prev.getCoordination().y,
						temp.getCoordination().x, temp.getCoordination().y);
				prev = temp;
			}
		}
	}

	public boolean isOutterFinalized() {
		return outterFinalized;
	}

	public void setOutterFinalized(boolean outterFinalized) {
		this.outterFinalized = outterFinalized;
	}

	public boolean isInnerFinalized() {
		return innerFinalized;
	}

	public void setInnerFinalized(boolean innerFinalized) {
		this.innerFinalized = innerFinalized;
	}

	public boolean hasIntersectWithInnersAndOutter(Line2D line) {
		boolean result = false;
		result = this.outterPolygon
				.hasIntersectWIthLineWithoutCommonPoint(line) || (!this.outterPolygon.containsDiameter(line) && !outterPolygon.isOnBoundary(line));
		if (!result) {
			for (MyPolygon polygon : this.getInnerPolygons()) {
				result = polygon.hasIntersectWIthLineWithoutCommonPoint(line)|| polygon.containsDiameter(line);
				if (result) {
					break;
				}
			}
		}
		return result;

	}

	// public Edge[] getEdge(){
	// ArrayList<Edge> edges=new ArrayList<Edge>();
	// Line2D toadd = new Line2D.Float(startPoint,endPoint);
	// if(!hasIntersectWithInnersAndOutter(toadd)){
	// Edge e = new Edge();
	// e.setP1(startPoint);
	// e.setP2(endPoint);
	// e.setDist(Point.distance(startPoint.x,startPoint.y,endPoint.x,endPoint.y));
	// e.v1 = startPoint.toString();
	// e.v2 = endPoint.toString();
	// edges.add(new Edge());
	// }
	// for (int i = 0; i < this.getOutterPolygon().npoints; i++) {
	// if()
	// }
	// }
	public Edge[] getEdges() {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < allpoints.size(); i++) {
			for (int j = i + 1; j < allpoints.size(); j++) {
				Line2D toadd = new Line2D.Float(allpoints.get(i), allpoints
						.get(j));
				if (!hasIntersectWithInnersAndOutter(toadd)) {
					Edge e = new Edge();
					e.setP1(allpoints.get(i));
					e.setP2(allpoints.get(j));
					e.setDist(Point.distance(allpoints.get(i).x, allpoints
							.get(i).y, allpoints.get(j).x, allpoints.get(j).y));
					e.v1 = allpoints.get(i).toString();
					e.v2 = allpoints.get(j).toString();
					edges.add(e);
				}
			}
		}
		Edge[] edges2 = new Edge[edges.size()];
		return edges.toArray(edges2);
	}

	public ArrayList<Point> getAllpoints() {
		return allpoints;
	}

	public void setAllpoints(ArrayList<Point> allpoints) {
		this.allpoints = allpoints;
	}

	public int getStarIndex() {
		return starIndex;
	}

	public void setStarIndex(int starIndex) {
		this.starIndex = starIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void calculateShortestPath() {
		// System.out.println(this.question.getStartPoint().toString());
		// System.out.println(this.question.getEndPoint().toString());
		Graph g = new Graph(getEdges());
		g.dijkstra(getStartPoint().toString());
		this.shortestPath = g.getPath(getEndPoint().toString());

	}

}
