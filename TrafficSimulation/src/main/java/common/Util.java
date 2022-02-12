package common;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Util {

    // crosswalks  {west, east, north, south}
    public static Color crosswalkColor[] = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};

    // road  {west.right, west.straight, north.right, north.straightORleft, east.right, east.straight, south.right, south.straightORleft}
    public static Color roadLight[] = {Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED};

    public static boolean goThreads = false;

    public static ArrayList<Point> semCoord = new ArrayList<>(
            Arrays.asList(/*North*/ new Point(410, 300), new Point(460, 300),
                    /*East*/ new Point(520, 310), new Point(520, 360), new Point(520, 410),
                    /*South*/ new Point(515, 515), new Point(460, 515),
                    /*West*/ new Point(400, 510), new Point(400, 460), new Point(400, 410)));

    public static void changeRoadLight(int index) {
        if (roadLight[index] == Color.RED) roadLight[index] = Color.GREEN;
        else if (roadLight[index] == Color.GREEN) roadLight[index] = Color.YELLOW;
        else if (roadLight[index] == Color.YELLOW) roadLight[index] = Color.RED;
    }

}
