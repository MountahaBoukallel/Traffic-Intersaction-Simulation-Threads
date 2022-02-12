package view;

import common.Util;
import model.Car;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Road extends JPanel {

    private final int LANE_WIDTH = 50;
    private final int LANE_LENGTH_EW = 400; // East, West
    private final int LANE_LENGTH_NS = 300; // North, South

    private ArrayList<Car> cars = new ArrayList<Car>();

    public Road() {
        super();
        this.setVisible(true);
        this.setSize(950, 850);
    }

//----------------------------------
    public void printCars() {
        for (int i = 0; i<cars.size(); i++) {
            System.out.println(cars.get(i));
        }
    }
//----------------------------------

    public void addCar(Car car){
        this.cars.add(car);
    }

    public boolean collision (Car vehicle) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (!vehicle.equals(car)) {
                if (vehicle.getY() == car.getY()) {
                    // W-E or E-W direction
                    if (vehicle.getX() + vehicle.getWidth() >= car.getX() || car.getX() + car.getWidth() >= vehicle.getX()) {
                        return true;
                    }
                } else if (vehicle.getX() == car.getX()) {
                    // N-S or S-N direction
                    if (vehicle.getY() + vehicle.getHeight() >= car.getY() || car.getY() + car.getHeight() >= vehicle.getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void paintCar(Graphics g, Car car) {
        g.setColor(car.getColor());
        g.drawRect(car.getX(), car.getY(), car.getWidth(), car.getHeight());
    }

    private void paintWest(Graphics g) {

        g.setColor(Color.WHITE);
        for (int y = 300; y <= 550; y += LANE_WIDTH) {
            if (y == 300 || y == 550) {
                g.fillRect(0, y, LANE_LENGTH_EW, 3);
            } else if (y == 400) {
                g.fillRect(0, y, LANE_LENGTH_EW - 40, 3);
            } else {
                for (int x = 340; x >= 0; x -= 40) {
                    g.fillRect(x, y, 20, 3);
                }
            }
        }

        // stop line
        g.fillRect(360, 400, 7, 153);

        // draw arrows
        try {
            BufferedImage img1 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\WtoE.png"));
            g.drawImage(img1, 310, 410, 40, 30, null);
            g.drawImage(img1, 310, 460, 40, 30, null);

            BufferedImage img2 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\WtoS.png"));
            g.drawImage(img2, 315, 510, 30, 30, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // draw traffic lights for road
        g.setColor(Util.roadLight[0]);
        g.fillOval(400, 510, 30, 30);

        g.setColor(Util.roadLight[1]);
        g.fillOval(400, 410, 30, 30);
        g.fillOval(400, 460, 30, 30);

    }

    private void paintEast(Graphics g) {

        g.setColor(Color.WHITE);
        for (int y = 300; y <= 550; y += LANE_WIDTH) {
            if (y == 300 || y == 550) {
                g.fillRect(550, y, LANE_LENGTH_EW, 3);
            } else if (y == 450) {
                g.fillRect(590, y, LANE_LENGTH_EW - 40, 3);
            } else {
                for (int x = 590; x < 950; x += 40) {
                    g.fillRect(x, y, 20, 3);
                }
            }
        }

        // stop line
        g.fillRect(583, 300, 7, 153);

        // draw arrows
        try {
            BufferedImage img3 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\EtoW.png"));
            g.drawImage(img3, 600, 360, 40, 30, null);
            g.drawImage(img3, 600, 410, 40, 30, null);

            BufferedImage img4 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\EtoN.png"));
            g.drawImage(img4, 600, 310, 30, 30, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // draw traffic lights for road
        g.setColor(Util.roadLight[4]);
        g.fillOval(520, 310, 30, 30);

        g.setColor(Util.roadLight[5]);
        g.fillOval(520, 360, 30, 30);
        g.fillOval(520, 410, 30, 30);

    }

    private void paintNorth(Graphics g) {

        g.setColor(Color.WHITE);
        for (int x = 400; x <= 550; x += LANE_WIDTH) {
            if (x == 400 || x == 550) {
                g.fillRect(x, 0, 3, LANE_LENGTH_NS + 3);
            } else if (x == 500) {
                g.fillRect(x, 0, 3, LANE_LENGTH_NS - 40 + 3);
            } else {
                for (int y = 240; y >= 0; y -= 40) {
                    g.fillRect(x, y, 3, 20);
                }
            }
        }

        // stop line
        g.fillRect(400, 260, 103, 7);

        // draw arrows
        try {
            BufferedImage img7 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\NtoW.png"));
            g.drawImage(img7, 410, 220, 30, 30, null);

            BufferedImage img8 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\NtoSE.png"));
            g.drawImage(img8, 460, 220, 30, 30, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // draw traffic lights for road
        g.setColor(Util.roadLight[2]);
        g.fillOval(410, 300, 30, 30);

        g.setColor(Util.roadLight[3]);
        g.fillOval(460, 300, 30, 30);

    }

    private void paintSouth(Graphics g) {

        g.setColor(Color.WHITE);
        for (int x = 400; x <= 550; x += LANE_WIDTH) {
            if (x == 400 || x == 550) {
                g.fillRect(x, 550, 3, LANE_LENGTH_NS);
            } else if (x == 450) {
                g.fillRect(x, 590, 3, LANE_LENGTH_NS - 40);
            } else {
                for (int y = 590; y <= 850; y += 40) {
                    g.fillRect(x, y, 3, 20);
                }
            }
        }

        // stop line
        g.fillRect(450, 583, 100, 7);

        // draw arrows
        try {
            BufferedImage img5 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\StoE.png"));
            g.drawImage(img5, 515, 600, 30, 30, null);

            BufferedImage img6 = ImageIO.read(new File("D:\\An3\\SCS\\TrafficSimulation\\images\\StoNW.png"));
            g.drawImage(img6, 460, 600, 30, 30, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // draw traffic lights for road
        g.setColor(Util.roadLight[6]);
        g.fillOval(515, 515, 30, 30);

        g.setColor(Util.roadLight[7]);
        g.fillOval(460, 515, 30, 30);

    }

    private void paintCrosswalkWest(Graphics g) {
        g.setColor(Util.crosswalkColor[0]);
        for (int y = 310; y < 550; y += 20) {
            g.fillRect(372, y, 23, 10);
        }
    }

    private void paintCrosswalkEast(Graphics g) {
        g.setColor(Util.crosswalkColor[1]);
        for (int y = 310; y < 550; y += 20) {
            g.fillRect(555, y, 23, 10);
        }
    }

    private void paintCrosswalkNorth(Graphics g) {
        g.setColor(Util.crosswalkColor[2]);
        for (int x = 410; x < 550; x += 20) {
            g.fillRect(x, 272, 10, 23);
        }
    }

    private void paintCrosswalkSouth(Graphics g) {
        g.setColor(Util.crosswalkColor[3]);
        for (int x = 410; x < 550; x += 20) {
            g.fillRect(x, 555, 10, 23);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        paintWest(g);
        paintEast(g);
        paintNorth(g);
        paintSouth(g);

        g.setColor(Color.WHITE);

        paintCrosswalkWest(g);
        paintCrosswalkEast(g);
        paintCrosswalkNorth(g);
        paintCrosswalkSouth(g);

        for (Car c : cars) {
            paintCar(g, c);
        }
    }

}
