package models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** One vertex of the graph, complete with mappings to neighbouring vertices */
public class Vertex implements Comparable<Vertex> {
	public final String name;
	public double dist = Double.MAX_VALUE; // MAX_VALUE assumed to be infinity
	public Vertex previous = null;
	public final Map<Vertex, Double> neighbours = new HashMap<Vertex, Double>();
	public Point coordination = new Point();
	public Vertex(String name) {
		this.name = name;
	}
	
	public Point getCoordination() {
		return coordination;
	}

	public void setCoordination(Point coordination) {
		this.coordination = coordination;
	}

	public Vertex(String name,Point coordination) {
		this.name = name;
		this.coordination = coordination;
	}

	public ArrayList<Vertex> getPath(ArrayList<Vertex> path) {
		if (this == this.previous) {
			path.add(this);
			return path;
		} else if (this.previous == null) {
			System.out.printf("%s(unreached)", this.name);
			throw new RuntimeException();
		} else {
			this.previous.getPath(path).add(this);
			return path;
		}
	}

	public void printPath() {
		if (this == this.previous) {
			System.out.printf("%s", this.name);
		} else if (this.previous == null) {
			System.out.printf("%s(unreached)", this.name);
		} else {
			this.previous.printPath();
			System.out.println(" -> " + this.name + "(" + this.dist + ")");
		}
	}

	public int compareTo(Vertex other) {

		return (int) (dist - other.dist);
	}
}