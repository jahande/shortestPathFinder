package models;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
 
public class Dijkstra {
   private static final Edge[] GRAPH = {
      new Edge("a", "b", 7),
      new Edge("a", "c", 9),
      new Edge("a", "f", 14),
      new Edge("b", "c", 10),
      new Edge("b", "d", 15),
      new Edge("c", "d", 11),
      new Edge("c", "f", 2),
      new Edge("d", "e", 6),
      new Edge("e", "f", 9),
   };
   private static final String START = "a";
   private static final String END = "e";
 
   public static void main(String[] args) 
   
   {
	   MyPolygon p = new MyPolygon();
	   p.addPoint(0, 0);
	   p.addPoint(1, 0);
	   p.addPoint(0, 1);
	   p.addPoint(1, 1);
	  
	   System.out.println(p.getIndexOf(new Point(1, 1)));
	   System.out.println(p.isOnBoundary(new Line2D.Float(0,0,1,1)));
	   
	   
      Graph g = new Graph(GRAPH);
      g.dijkstra(START);
      g.printPath(END);
      //g.printAllPaths();
      System.out.println(new Point(11,12).toString());
   }
}
