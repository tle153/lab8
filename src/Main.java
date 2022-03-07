import processing.core.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class Main
   extends PApplet {
    public void settings() {
        size(500, 500);
    }

    public void setup() {
        background(180);
        noLoop();
    }

    public void draw() {

        double x, y, z;

        // turn file into points list
        String[] lines = loadStrings("positions.txt");
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > 0) {
                String[] words = lines[i].split(",");
                x = Double.parseDouble(words[0]);
                y = Double.parseDouble(words[1]);
                z = Double.parseDouble(words[2]);
                Point p = new Point(x, y, z);
                points.add(p);
            }
        }

        // remove all z > 2.0
        points = points.stream().filter(p->p.getZ()<=2.0).collect(Collectors.toList());
        // scale down by .5
        points = points.stream().map(
                p->new Point(p.getX()*.5,
                        p.getY()*.5,
                        p.getZ()*.5)).collect(Collectors.toList());
        // Translate all the points by {-150, -37}
        points = points.stream().map(
                p->new Point(
                        p.getX()-150,
                        p.getY()-37,
                        p.getZ())).collect(Collectors.toList());
        _drawAndWritePoints(points);
    }

    public void _drawAndWritePoints(List<Point> points)
    {
        try {
            FileWriter myWriter = new FileWriter("drawMe.txt");
            for (Point p : points) {
                myWriter.write(p + "\n");
                ellipse((int) p.getX(), (int) p.getY(), 1, 1);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

  //necessary main to use Processing to draw 
   public static void main(String[] args) {
        PApplet.main("Main");
    }
}
