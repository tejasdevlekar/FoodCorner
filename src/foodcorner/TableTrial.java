package foodcorner;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class TableTrial extends JFrame{
    JTable table;
    DefaultTableModel model;
    
    Connection conn;
    Statement stmt;
    ResultSet rs;
    public TableTrial(){
        super("Title");
        setLayout(new FlowLayout());
        
        DefaultTableModel model = new DefaultTableModel();
        String columnNames[] = {"Order_No", "Time", "Type", "Extra", "Qty", "Rate", "Total"};
        model.setColumnIdentifiers(columnNames);
        
        table = new JTable();
        table.setModel(model);
        table.setFillsViewportHeight(true);
        
        add(new JScrollPane(table));
        
        try {       //Database Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcornerdb", "root", "");
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery("select * from sales");
            String columns[] = new String[7];
            
            while(rs.next()){
                columns[0] = rs.getString("Order_No");
                columns[1] = rs.getString("Time");
                columns[2] = rs.getString("Type");
                columns[3] = rs.getString("Extra");
                columns[4] = rs.getString("Qty");
                columns[5] = rs.getString("Rate");
                columns[6] = rs.getString("Total");
                
                model.addRow(columns);
                
            }
            
        } catch (Exception e) {
            System.out.println("Error in databaseConnect()");
            e.printStackTrace();
        }
        
        JRadioButton one = new JRadioButton("One");
        JRadioButton two = new JRadioButton("Two");
        
        ButtonGroup group = new ButtonGroup();
        group.add(one);
        group.add(two);
        add(one);
        add(two);
        
        
    }
    
    
    public static void main(String[] args) {
        TableTrial t = new TableTrial();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setSize(500, 600);
        t.setVisible(true);
    }
    
}