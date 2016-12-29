public class FastCollinearPoints {
    
    
//Think of p as the origin.
//For each other point q, determine the slope it makes with p.
//Sort the points according to the slopes they makes with p.
//Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
    public FastCollinearPoints(Point[] points) {
        
        for (Point point : points) {
        // grab p
            Point p = point;
            // sort the array based on slope
            
        }
    
    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments() {
    
    }       // the number of line segments
    public LineSegment[] segments()   {
    
    }             // the line segments
}