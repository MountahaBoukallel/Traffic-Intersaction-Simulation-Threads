package common;

import model.Car;
import model.Direction;
import view.Road;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CarsGenerator {

    private final int HEAVY_TRAFFIC = 3;
    private final int MEDIUM_TRAFFIC = 2;
    private final int LIGHT_TRAFFIC = 1;

    private /*final*/ ArrayList<Point> STARTING_POINTS = new ArrayList<Point>(
            Arrays.asList(/*North*/ new Point(410, -30), new Point(460, -30),
                    /*East*/ new Point(950, 310), new Point(950, 360), new Point(950, 410),
                    /*South*/ new Point(510, 850), new Point(460, 850),
                    /*West*/ new Point(-30, 510), new Point(-30, 460), new Point(-30, 410)));

    public CarsGenerator() {}

    public ArrayList<Thread> genCars(int traffic, Road road) {
        // generate cars depending on the traffic flow given as argument
        int n;
        if (traffic == HEAVY_TRAFFIC) n = 50;
        else if (traffic == MEDIUM_TRAFFIC) n= 30;
        else n = 10;

        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < n; i++) {
            Random rand = new Random();
            int index = rand.nextInt(10);
            Point p = STARTING_POINTS.get(index);
            if (index == 0 || index == 1) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(p.y);
                p.y = p.y - 50;
                System.out.println(p.y);
                STARTING_POINTS.remove(index);
                STARTING_POINTS.add(index, p);
            } else if (index == 2 || index == 3 || index == 4) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(p.x);
                p.x = p.x + 50;
                System.out.println(p.x);
                STARTING_POINTS.remove(index);
                STARTING_POINTS.add(index, p);
            } else if (index == 5 || index == 6) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(p.y);
                p.y = p.y + 50;
                System.out.println(p.y);
                STARTING_POINTS.remove(index);
                STARTING_POINTS.add(index, p);
            } else if (index == 7 || index == 8 || index == 9) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(p.x);
                p.x = p.x - 50;
                System.out.println(p.x);
                STARTING_POINTS.remove(index);
                STARTING_POINTS.add(index, p);
            }
            Direction dir;

            Car car = new Car(Color.WHITE, p.x, p.y, 40, 30, /*rand.nextInt(5) + 10*/ 15, index);
            road.addCar(car);

            Thread t = new Thread(car);
            threads.add(t);

            System.out.println("x=" + p.x + ", y=" + p.y + ", index=" + index);
        }

        return threads;
    }

}
