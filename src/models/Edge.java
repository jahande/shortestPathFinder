package models;

import java.awt.Point;

public class Edge {
    public String v1, v2;
    public  double dist;
    public Point p1;
    public Point p2;
    
    public Edge() {
		super();
	}

	public Edge(String v1, String v2, int dist) {
       this.v1 = v1;
       this.v2 = v2;
       this.dist = dist;
       
    }

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}
 }