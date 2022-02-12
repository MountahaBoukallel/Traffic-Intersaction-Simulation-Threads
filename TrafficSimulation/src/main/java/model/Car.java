package model;

import common.Util;

import java.awt.*;
import java.util.Random;

public class Car implements Runnable {

    private Color color;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private int lane;
    private Direction direction;
    private int decision;
    private int indexSem;

    private boolean proximity = false;
    private boolean passed = false;

    private final int SCREEN_WIDTH = 950;
    private final int SCREEN_HEIGHT = 850;

    public Car(Color color, int x, int y, int width, int height, int speed, int lane) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.lane = lane;

        if (lane == 0) {
            this.direction = Direction.N_W;
            this.indexSem = 2;
        }
        else if (lane == 1) {
            this.direction = Direction.N_SE;
            this.indexSem = 3;
        }
        else if (lane == 2) {
            this.direction = Direction.E_N;
            this.indexSem = 4;
        }
        else if (lane == 3) {
            this.direction = Direction.E_W;
            this.indexSem = 5;
        }
        else if (lane == 4) {
            this.direction = Direction.E_W;
            this.indexSem = 5;
        }
        else if (lane == 5) {
            this.direction = Direction.S_E;
            this.indexSem = 6;
        }
        else if (lane == 6) {
            this.direction = Direction.S_NW;
            this.indexSem = 7;
        }
        else if (lane == 7) {
            this.direction = Direction.W_S;
            this.indexSem = 0;
        }
        else if (lane == 8) {
            this.direction = Direction.W_E;
            this.indexSem = 1;
        }
        else if (lane == 9) {
            this.direction = Direction.W_E;
            this.indexSem = 1;
        }

        if (direction == Direction.S_NW || direction == Direction.N_SE) {
            Random rand = new Random();
            this.decision = rand.nextInt(2);
        }
    }

    public void run() {
        while (Util.goThreads) {
            try {
                this.move(speed);
                Thread.sleep(100);
                while (Util.roadLight[indexSem] == Color.RED && !passed && proximity) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move(int speed){
        System.out.println(this.lane + " - " + this.direction + this.x + ", " + this.y);
        if (this.isOnScreen()) {
            if (this.direction == Direction.W_E) {
                if (this.getX() >= Util.semCoord.get(lane).x) {
                    passed = true;
                }
                if (Math.abs(this.getX() - Util.semCoord.get(lane).x) < 100) {
                    proximity = true;
                }
                moveWestEast(speed);
            } else if (this.direction == Direction.W_S) {
                if (this.getX() >= Util.semCoord.get(lane).x) {
                    passed = true;
                }
                if (Math.abs(this.getX() - Util.semCoord.get(lane).x) < 100) {
                    proximity = true;
                }
                moveWestSouth(speed);
            } else if (direction == Direction.S_NW) {
                if (this.getY() <= Util.semCoord.get(lane).y) {
                    passed = true;
                }
                if (Math.abs(this.getY() - Util.semCoord.get(lane).y) < 100) {
                    proximity = true;
                }
                moveSouthNorthWest(speed);
            } else if (direction == Direction.S_E) {
                if (this.getY() <= Util.semCoord.get(lane).y) {
                    passed = true;
                }
                if (Math.abs(this.getY() - Util.semCoord.get(lane).y) < 100) {
                    proximity = true;
                }
                moveSouthEast(speed);
            } else if (direction == Direction.E_W) {
                if (this.getX() <= Util.semCoord.get(lane).x) {
                    passed = true;
                }
                if (Math.abs(this.getX() - Util.semCoord.get(lane).x) < 100) {
                    proximity = true;
                }
                moveEastWest(speed);
            } else if (direction == Direction.E_N) {
                if (this.getX() <= Util.semCoord.get(lane).x) {
                    passed = true;
                }
                if (Math.abs(this.getX() - Util.semCoord.get(lane).x) < 100) {
                    proximity = true;
                }
                moveEastNorth(speed);
            } else if (direction == Direction.N_SE) {
                if (this.getY() >= Util.semCoord.get(lane).y) {
                    passed = true;
                }
                if (Math.abs(this.getY() - Util.semCoord.get(lane).y) < 100) {
                    proximity = true;
                }
                moveNorthSouthEast(speed);
            } else if (direction == Direction.N_W) {
                if (this.getY() >= Util.semCoord.get(lane).y) {
                    passed = true;
                }
                if (Math.abs(this.getY() - Util.semCoord.get(lane).y) < 100) {
                    proximity = true;
                }
                moveNorthWest(speed);
            }
        }
    }

    private boolean isOnScreen() {
        if (lane == 0 || lane == 1) {
            return (this.x <= SCREEN_WIDTH && this.y <=SCREEN_HEIGHT && this.x + this.height >= 0);
        } else if (lane == 2 || lane == 3 || lane == 4) {
            return (this.x + this.width >= 0 && this.y <=SCREEN_HEIGHT && this.x + this.height >= 0);
        }else if (lane == 5 || lane == 6) {
            return (this.x <= SCREEN_WIDTH && this.x + this.width >= 0 && this.x + this.height >= 0);
        } else {
            // lane == 7 || lane == 8 || lane == 9
            return (this.x <= SCREEN_WIDTH && this.y <=SCREEN_HEIGHT && this.x + this.height >= 0);
        }
    }

    private void moveWestEast(int speed) {
        this.incX(speed);
        if (this.getLane() == 9 && this.getX() >= 400 && this.getY() < 460){
            this.incY(speed - 5);
        } else if (this.getLane() == 8 && this.getX() >= 400 && this.getY() < 510) {
            this.incY(speed - 5);
        }
    }

    private void moveWestSouth(int speed) {
        if (this.getX() >= 405) {
            this.incY(speed);
        } else {
            this.incX(speed);
        }
    }

    private void moveSouthNorthWest(int speed) {
        if (this.decision == 0) {
            // North
            decY(speed);
            if (this.getY() < 450 && this.getX() < 510) {
                incX(speed - 5);
            }
        } else {
            // West
            if (this.getY() > 360) {
                decY(speed);
            } else {
                decX(speed);
            }
        }

    }

    private void moveSouthEast(int speed) {
        if (this.getY() < 510) {
            incX(speed);
        } else {
            decY(speed);
        }
    }

    private void moveEastWest(int speed) {
        this.decX(speed);
        if (this.getLane() == 4 && this.getX() <= 500 && this.getY() > 360){
            this.decY(speed - 5);
        } else if (this.getLane() == 3 && this.getX() <= 550 && this.getY() > 310) {
            this.decY(speed - 5);
        }
    }

    private void moveEastNorth(int speed) {
        if (this.getX() < 520) {
            decY(speed);
        } else {
            decX(speed);
        }
    }

    private void moveNorthSouthEast(int speed) {
        if (this.decision == 0) {
            // South
            incY(speed);
            if (this.getY() > 400 && this.getX() > 410) {
                decX(speed - 5);
            }
        } else {
            // East
            if (this.getY() < 460) {
                incY(speed);
            } else {
                incX(speed);
            }
        }
    }

    private void moveNorthWest(int speed) {
        if (this.getY() > 300) {
            decX(speed);
        } else {
            incY(speed);
        }
    }

    public Color getColor() {
        return this.color;
    }

    public int getLane() {
        return this.lane;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void incX(int speed) {
        this.x += speed;
    }

    public void incY(int speed) {
        this.y += speed;
    }

    public void decX(int speed) {
        this.x -= speed;
    }

    public void decY(int speed) {
        this.y -= speed;
    }

}
