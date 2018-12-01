package com.sjsu.cmpe.sstreet.simulatedstation.service;

import com.sjsu.cmpe.sstreet.simulatedstation.Contact;
import com.sjsu.cmpe.sstreet.simulatedstation.MyGui;
import com.sjsu.cmpe.sstreet.simulatedstation.model.SmartNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public class Smartnodegui extends JFrame {

        private final static List<SmartNode> smartNodeList = new ArrayList<SmartNode>();
        private JTextField Snodename;
        private JTextField Snodeinsdate;
        private JTextField Snodelocation;
        private JTextField Snodemodel;
        private JTextField Snodemake;
        private JButton ADDButton;
        private JButton UPDATEButton;
        private JButton DELETEButton;
        private JTextField Snodeid;
        private JPanel Smartnodepanel;

        public Smartnodegui() {
            super("Smart Node panel");



            ADDButton.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        theQuery("INSERT into sensors.smartnodebook (id,name,make,model,installationdate,location) values('"
                                + Snodeid.getText() + "','" +
                                Snodename.getText() + "','" +
                                Snodemake.getText() + "','" + Snodemodel.getText() + "','" +
                                Snodeinsdate.getText() + "','" + Snodelocation.getText() + "')");

                    } catch (Exception ex) {}

                    }
                });


            UPDATEButton.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        theQuery("update sensors.smartnodebook set id = '"+Snodeid.getText()+"',name= '"+
                                        Snodename.getText()+"',make= '"+Snodemake.getText()+"',model= '"+Snodemodel.getText()+"',installationdate= '"+
                                Snodeinsdate.getText()+"', location= '"+Snodelocation.getText()+"' where id = "+
                                        Snodeid.getText());
                    }

                    catch (Exception ex){ }

                }
            });
            DELETEButton.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        theQuery("delete from sensors.smartnodebook where id =" +Snodeid.getText());
                    }
                    catch (Exception ex){ }


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
            JFrame frame = new JFrame("Smartnodegui");
            frame.setContentPane(new Smartnodegui().Smartnodepanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }


