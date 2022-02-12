package common;

import java.awt.*;

public class TrafficLight implements Runnable{

    private int id;

    public TrafficLight(int id) {
        this.id = id;
    }

    public void run() {
        try {
            while (Util.goThreads) {
                if (id == 0) {
                    Util.roadLight[id] = Color.GREEN;
                    Thread.sleep(3000);

                    Util.roadLight[id] = Color.YELLOW;
                    Thread.sleep(1000);

                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(4000);

                    Util.roadLight[id] = Color.GREEN;
                    Thread.sleep(4000);
                }
                if (id == 1 || id == 5) {
                    Util.roadLight[id] = Color.GREEN;
                    Thread.sleep(3000);

                    Util.roadLight[id] = Color.YELLOW;
                    Thread.sleep(1000);

                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(8000);
                }
                if (id == 2 || id == 3) {
                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(4000);

                    Util.roadLight[id] = Color.GREEN;
                    Thread.sleep(3000);

                    Util.roadLight[id] = Color.YELLOW;
                    Thread.sleep(1000);

                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(4000);
                }
                if (id == 4) {
                    Util.roadLight[id] = Color.GREEN;
                    Thread.sleep(7000);

                    Util.roadLight[id] = Color.YELLOW;
                    Thread.sleep(1000);

                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(4000);
                }
                if (id == 6 || id == 7) {
                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(8000);

                    Util.roadLight[id] = Color.GREEN;
                    Thread.sleep(3000);

                    Util.roadLight[id] = Color.YELLOW;
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
