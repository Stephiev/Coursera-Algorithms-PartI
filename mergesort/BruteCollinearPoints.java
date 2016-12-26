/******************************************************************************
  *  Stephanie Vasquez-Soltero
  *  December 21st, 2016
  * 
  *  Checking collinearity of 4 points given an array of points - brute 
  *  force approach
  * 
  ******************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    private LineSegment[] allLineSegments;
    
    // Testing collinearity
    // Given 3 points, "if the line segments AB and BC have the same slope, then A, B, C are necessarily collinear."
    // So given 4 points P, Q, R, S, then if PQ and PR have the same slope AND PQ and PS have the same slope then all
    // 4 points lie on the same line. 
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        Arrays.sort(points); 
        repeatedPoints(points);
        double epsilon = 0.00001;
        double pqSlope;
        double prSlope;
        double psSlope;
        ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
        
        for (int p = 0; p < points.length - 3; p++) { // -3 since we're looking at 4 points at a time
            for (int q = p + 1; q < points.length - 2; q++) { // q is always the one right next to p
                for (int r = q + 1; r < points.length - 1; r++) { // cannot be p + x because they are changing at different times
                    for (int s = r + 1; s < points.length; s++) {
                        pqSlope = points[p].slopeTo(points[q]);
                        prSlope = points[p].slopeTo(points[r]);
                        psSlope = points[p].slopeTo(points[s]);
                        // Comparing floats
                        if (Math.abs(pqSlope - prSlope) < epsilon && Math.abs(pqSlope - psSlope) < epsilon) {
                            lineSegments.add(new LineSegment(points[p], points[s]));      
                        }
                    }
                }
            }
        }
        allLineSegments = lineSegments.toArray(new LineSegment[lineSegments.size()]);     
    } 
    
    // the number of line segments
    public int numberOfSegments() {
        return allLineSegments.length;
    }   
    
    // the line segments
    public LineSegment[] segments()   {
        // From findbugs
//      Returns a reference to the mutable object stored in the instance variable 'allLineSegments',
//      which exposes the internal representation of the class 'BruteCollinearPoints'. Instead, create 
//      a defensive copy of the object referenced by 'allLineSegments' and return the cop
        return Arrays.copyOf(allLineSegments, allLineSegments.length); 
    }            
    
    // Since the array is ordered already, only need to check neighbors
    public void repeatedPoints(Point[] points) {
        for (int j = 0; j < points.length - 1; j++) {
            if (points[j].compareTo(points[j + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public static void main(String[] args) {
        
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
    
    /*************************************************************************
      *  Compilation:  javac LineSegment.java
      *  Execution:    none
      *  Dependencies: Point.java
      *
      *  An immutable data type for Line segments in the plane.
      *  For use on Coursera, Algorithms Part I programming assignment.
      *
      *  DO NOT MODIFY THIS CODE.
      *
      *************************************************************************/
    
    public class LineSegment {
        private final Point p;   // one endpoint of this line segment
        private final Point q;   // the other endpoint of this line segment
        
        /**
         * Initializes a new line segment.
         *
         * @param  p one endpoint
         * @param  q the other endpoint
         * @throws NullPointerException if either <tt>p</tt> or <tt>q</tt>
         *         is <tt>null</tt>
         */
        public LineSegment(Point p, Point q) {
            if (p == null || q == null) {
                throw new NullPointerException("argument is null");
            }
            this.p = p;
            this.q = q;
        }
        
        
        /**
         * Draws this line segment to standard draw.
         */
        public void draw() {
            p.drawTo(q);
        }
        
        /**
         * Returns a string representation of this line segment
         * This method is provide for debugging;
         * your program should not rely on the format of the string representation.
         *
         * @return a string representation of this line segment
         */
        public String toString() {
            return p + " -> " + q;
        }
        
        /**
         * Throws an exception if called. The hashCode() method is not supported because
         * hashing has not yet been introduced in this course. Moreover, hashing does not
         * typically lead to good *worst-case* performance guarantees, as required on this
         * assignment.
         *
         * @throws UnsupportedOperationException if called
         */
        public int hashCode() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    
}

