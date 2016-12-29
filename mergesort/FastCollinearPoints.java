public class FastCollinearPoints {
    
    
//Think of p as the origin.
//Sort the points according to the slopes they makes with p.
//For each other point q, determine the slope it makes with p.
//Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
   
    public FastCollinearPoints(Point[] points) {
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        // grab p
        for (Point p : points) {
            
            //Sort the points according to the slopes they makes with p.
            Arrays.sort(sortedPoints, point.slopeOrder());
            
            
            //For each other point q, determine the slope it makes with p.
            for (int q = 1; q < sortedPoints.length; q++) { // start at 1 to avoid p
                // Check slopes
                
            }
            
        }
    
    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments() {
    
    }       // the number of line segments
    public LineSegment[] segments()   {
    
    }             // the line segments
}