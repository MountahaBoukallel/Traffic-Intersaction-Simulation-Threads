package controller;

import common.CarsGenerator;
import common.TrafficLight;
import common.Util;
import view.Road;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimulationManager implements Runnable, ActionListener {

    private Road road;
    private CarsGenerator carsGenerator;
    private ArrayList<Thread> carsThreads;

    private JFrame frame;

    private ButtonGroup trafficType;
    private JRadioButton heavy = new JRadioButton("Heavy");
    private JRadioButton medium = new JRadioButton("Medium");
    private JRadioButton light = new JRadioButton("Light");
    private JButton startSimulation = new JButton("Start");
    private JButton stopSimulation = new JButton("Stop");

    boolean running;

    public SimulationManager() {

        this.road = new Road();
        this.carsGenerator = new CarsGenerator();
        this.carsThreads = new ArrayList<Thread>();

        this.frame = new JFrame("Traffic simulator");
        this.frame.setLayout(null);
        this.frame.setSize(this.road.getWidth() + 120, 890);

        this.frame.add(road);
        this.trafficType = new ButtonGroup();
        this.trafficType.add(heavy);
        this.trafficType.add(medium);
        this.trafficType.add(light);
        this.light.setSelected(true);
        heavy.setBounds(950, 150, 100, 20);
        medium.setBounds(950, 170, 100, 20);
        light.setBounds(950, 190, 100, 20);
        startSimulation.setBounds(960, 750, 80, 30);
        stopSimulation.setBounds(960, 800, 80, 30);
        JLabel trafficJLabel = new JLabel("Traffic type");
        trafficJLabel.setBounds(960, 110, 80, 30);

        this.frame.add(trafficJLabel);
        this.frame.add(heavy);
        this.frame.add(medium);
        this.frame.add(light);
        this.frame.add(startSimulation);
        this.frame.add(stopSimulation);

        startSimulation.addActionListener(this);
        stopSimulation.addActionListener(this);

        this.running = false;

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

    }

    private void prepareLightThreads() {
        for (int i = 0; i < Util.roadLight.length; i++) {
            TrafficLight tl = new TrafficLight(i);
            Thread t = new Thread(tl);
            t.start();
        }
    }

    public void run() {
        while (running) {
            this.road.repaint();
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(startSimulation)) {
            if (!running) {
                Util.goThreads = true;
                prepareLightThreads();
                if (this.heavy.isSelected()) {
                    this.carsThreads = this.carsGenerator.genCars(3, this.road);
                } else if (this.medium.isSelected()) {
                    this.carsThreads = this.carsGenerator.genCars(2, this.road);
                } else if (this.light.isSelected()) {
                    this.carsThreads = this.carsGenerator.genCars(1, this.road);
                }
                running = true;
                road.printCars();
                Thread t = new Thread(this);
                t.start();
                carsThreads.forEach(Thread::start);
            }
            System.out.println("Start simulation");
        } else if (actionEvent.getSource().equals(stopSimulation)) {
            running = false;
            Util.goThreads = false;
            System.out.println("Stop simulation");
        }
    }

}
