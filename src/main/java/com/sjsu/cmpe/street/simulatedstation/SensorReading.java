package com.sjsu.cmpe.street.simulatedstation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;

public class SensorReading extends JFrame{

    private JPanel Sensorreadingpanel;
    private JTextField Locationenteringfield;
    private JLabel LocationLabel;
    private JPanel Temperatureshowingfield;

    public SensorReading() {
        Locationenteringfield.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rannum= new Random();
                int x = rannum.nextInt(98);
                JOptionPane.showMessageDialog(null,x+"Degrees fahrenheit","Temperature at "+Locationenteringfield.getText(),
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SensorReading");
        frame.setContentPane(new SensorReading().Sensorreadingpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
