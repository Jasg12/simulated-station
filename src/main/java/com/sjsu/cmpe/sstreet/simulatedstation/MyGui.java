package com.sjsu.cmpe.sstreet.simulatedstation;


import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import com.sjsu.cmpe.sstreet.simulatedstation.service.Smartnodegui;
import org.springframework.data.jpa.repository.Query;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.JPanel;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.*;

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
    private JTextField Sensoridtextfield;
    private JLabel SensoridLabel;
    private final static List<Contact> sensorbook = new ArrayList<Contact>();

    public MyGui() {
        super("Sensor panel");
        Addbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        theQuery("INSERT into sensors.sensorbook (Sensorid,name,location,type) values('" + Sensoridtextfield.getText()+"','"+
                                SensornametextField.getText() +"','"+
                                Locationtextfield.getText() +"','"+ Typetextfield.getText()+"')");

                    }
                        catch (Exception ex){ }
                        {
                        }
               // int i;
                //sensorbook.add(new Contact(SensornametextField.getText(),Locationtextfield.getText(),
                  //      Typetextfield.getText()));
                //for(i=0;i<sensorbook.size();i++)
                //{
                //JOptionPane.showMessageDialog(null,"Sensor name is"+sensorbook.get(i).getSensorname(),"Sensor"+(i),
                  //      JOptionPane.PLAIN_MESSAGE);
                //JOptionPane.showMessageDialog(null,"Sensor type is"+sensorbook.get(i).getType(),"Sensor"+(i),
                  //      JOptionPane.PLAIN_MESSAGE );
                //JOptionPane.showMessageDialog(null,"Sensor location is"+sensorbook.get(i).getLocation(),"Sensor"+(i),
                  //      JOptionPane.PLAIN_MESSAGE);}
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


        Updatebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   theQuery("update sensors.sensorbook set Sensorid = '"+Sensoridtextfield.getText()+"',name= '"+SensornametextField.getText()+"',location= '" +
                           Locationtextfield.getText()+"', type= '"+Typetextfield.getText()+"' where Sensorid = "+Sensoridtextfield.getText());
               }

                catch (Exception ex){ }


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
                try{
                    theQuery("delete from sensors.sensorbook where Sensorid =" +Sensoridtextfield.getText());
                }
                catch (Exception ex){ }

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

    }
    public void theQuery(String query)
    {
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sensors?autoReconnect=true&useSSL=false","root","anjala");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Query Executed" );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage() );
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyGui");
        frame.setContentPane(new MyGui().newSensorrootpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
