import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;



public class BruteCollinearPoints {
    ////        I moved the whole build-line-segments-calculation/loops process from under segments() to constructor

    private LineSegment[] allLineSegments;
    private int amountOfSegments;
    // Testing whether 3 points are collinear 
    // Given 3 points, "if the line segments AB and BC have the same slope, then A, B, C are necessarily collinear."
    // So given 4 points P, Q, R, S, then if PQ and PR have the same slope AND PQ and PS have then same slope then all
    // 4 points lie on the same line. 
          public BruteCollinearPoints(Point[] points) {
              double pqSlope;
              double prSlope;
              double psSlope;
              ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
                   Arrays.sort(points); 
                   
                   for (int p = 0; p < points.length - 3; p++) { // -3 since we're looking at 4 points at a time
                       for (int q = p + 1; q < points.length - 2; q++) { // q is always the one right next to p
                           for (int r = p + 2; r < points.length - 1; r++) {
                               for (int s = p + 3; s < points.length; s++) {
                                   pqSlope = points[p].slopeTo(points[q]);
                                   prSlope = points[p].slopeTo(points[r]);
                                   psSlope = points[p].slopeTo(points[s]);
                                   if (pqSlope == prSlope && pqSlope == psSlope) {
                                       lineSegments.add(new LineSegment(points[p], points[s]));      
                                   }
                               }
                           }
                       }
                   }
                   
             
              
              amountOfSegments = lineSegments.size();
          
          }   // finds all line segments containing 4 points
  public int numberOfSegments() {
      return amountOfSegments;
  }   // the number of line segments
   public LineSegment[] segments()   {
       return allLineSegments; // convert array list to array?
   }             // the line segments

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
//    for (LineSegment segment : collinear.segments()) {
//        StdOut.println(segment);
//        segment.draw();
//    }
//    StdDraw.show();
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

