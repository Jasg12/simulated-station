package com.sjsu.cmpe.sstreet.simulatedstation;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JPanel;

public class MyGui extends JFrame   {
    private JPanel newSensorrootpanel;
    private JPanel westjpanel;
    private JPanel eastjpanel;
    private JPanel southjpanel;
    private JPanel northjpanel;
    private JLabel Locationlabel;
    private JLabel TypeLabel;
    private JTextField SensornametextField;
    private JTextField Locationtextfield;
    private JTextField Typetextfield;
    private JButton Addbutton;
    private JButton Updatebutton;
    private JButton Deletebutton;
    private JLabel Sensorlabel;
    private final static List<Contact> sensorbook = new ArrayList<Contact>();

    public MyGui() {
        super("Sensor panel");
        SensornametextField.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        SensornametextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        Addbutton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                sensorbook.add(new Contact(SensornametextField.getText(),Locationtextfield.getText(),
                        Typetextfield.getText()));
                for(i=0;i<sensorbook.size();i++)
                {
                JOptionPane.showMessageDialog(null,"Sensor name is"+sensorbook.get(i).getSensorname(),"Sensor"+(i),
                        JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(null,"Sensor type is"+sensorbook.get(i).getType(),"Sensor"+(i),
                        JOptionPane.PLAIN_MESSAGE );
                JOptionPane.showMessageDialog(null,"Sensor location is"+sensorbook.get(i).getLocation(),"Sensor"+(i),
                        JOptionPane.PLAIN_MESSAGE);}
            }
        });
        Deletebutton.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        Updatebutton.addComponentListener(new ComponentAdapter() {
        });
        Updatebutton.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        Updatebutton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Deletebutton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Deletebutton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Locationtextfield.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Typetextfield.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    } public static void main(String[] args) {
        JFrame frame = new JFrame("MyGui");
        frame.setContentPane(new MyGui().newSensorrootpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
