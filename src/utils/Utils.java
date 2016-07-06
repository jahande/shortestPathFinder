package utils;

import java.awt.geom.Line2D;

public class Utils {
	public boolean hasIntersect(int x11,int y11,int x12,int y12,int x21,int y21,int x22,int y22){
		throw new RuntimeException();
//		Line2D line1 = new Line2D.Float(x11, y11, x12, y12);
//		Line2D line2 = new Line2D.Float(x21, y21, x22, y22);
//		boolean result = line2.intersectsLine(line1);
//		return result;
	}
	public static void main(String[] args) {
		Line2D line1 = new Line2D.Float(101, 101, 200, 200);
		Line2D line2 = new Line2D.Float(100, 100, 0, 200);
		boolean result = line2.intersectsLine(line1);
		System.out.println(result); // => true
	}
	
	public boolean hasIntersectWithoutCommonPoint(Line2D l1,Line2D l2){
		if(!hasCommonPoint(l1,l2) && l1.intersectsLine(l2)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * check if there is exact two or more equal points.
	 * note that in this question if two lines have 2 or more of common point, they are exact one line even in start and end
	 * @param l1
	 * @param l2
	 * @return
	 */
	public boolean hasCommonPoint(Line2D l1,Line2D l2){
		if(		l1.getP1().equals(l2.getP1()) ||
				l1.getP1().equals(l2.getP2()) ||
				l1.getP2().equals(l2.getP1()) ||
				l1.getP2().equals(l2.getP2()) 
		){
			return true;
		}else{
			return false;
		}
		
	}
	public Line2D deleteTheEnds(Line2D line) {
		
		return null;
	}
	
}
