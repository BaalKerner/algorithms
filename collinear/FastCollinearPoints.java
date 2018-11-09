/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        this.segments = new ArrayList<LineSegment>();

        Point[] temp = new Point[points.length - 1];

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            for (int j = 0, k = 0; j < points.length; j++) {
                if (!p.equals(points[j])) {
                    temp[k++] = points[j];
                }
            }

            Arrays.sort(temp, p.slopeOrder());

            for (int j = 0; j < temp.length; j++) {
                int k = 0;
                Point current = temp[j];

                Point start;
                Point finish;

                if (p.compareTo(current) < 0) {
                    start = p;
                    finish = current;
                } else {
                    start = current;
                    finish = p;
                }

                while((j + k < temp.length) && (p.slopeTo(current) == p.slopeTo(temp[j + k]))) {
                    current = temp[j + k];
                    k++;

                    if (current.compareTo(start) < 0) {
                        start = current;
                    }

                    if (current.compareTo(finish) > 0) {
                        finish = current;
                    }
                }

                if (k >= 3 && !segmentPresent(new LineSegment(start, finish))) {
                    this.segments.add(new LineSegment(start, finish));
                }

                j += k - 1;
            }
        }
    }

    public int numberOfSegments() {
        return this.segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] segm = new LineSegment[this.segments.size()];
        return this.segments.toArray(segm);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private boolean segmentPresent(LineSegment newSegment) {
        for (LineSegment segment : segments) {
            if (segment.toString().equals(newSegment.toString())) {
                return true;
            }
        }

        return false;
    }
}
