package databaseproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;


public class mainProgram extends JFrame implements ActionListener {

    Connection conn=null;
    ResultSet rs=null;
    Statement st=null;
//Initializing the Panels
	JPanel southPanel= new JPanel();
	//End of Initializing Panels
	
	
	//Adding the Welcome Text JLabel
	JLabel welcomeText = new JLabel("Welcome to Yasser's Library!");
	//End of Adding the welcome Text
	
        JButton button1 = new JButton("Add Transaction");
	
	//Initializing the Table
	static JTable bookTable = new JTable() {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false; //cells are set to be read-only              
	        };
	    };
        
        static JTable memberTable = new JTable() {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false; //cells are set to be read-only              
	        };
	    };
        
        static JTable transactionsTable = new JTable() {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false; //cells are set to be read-only              
	        };
	    };
        
	    
	static DefaultTableModel model = new DefaultTableModel(){};
        static DefaultTableModel model2 = new DefaultTableModel(){};
         static DefaultTableModel model3 = new DefaultTableModel(){};
	//End of initializing the Table
    
    
    public static void main(String[] args) {
		
		
		mainProgram graphicUserInterface = new mainProgram();
		bookTable.setModel(model);
                memberTable.setModel(model2);
                transactionsTable.setModel(model3);
                
		}
    
    public mainProgram(){
		
		 //GUI specifications
		 setLayout(new BorderLayout());
		 setSize(1024,768);
		 setTitle("Yasser's Library");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLocationRelativeTo(null);
		 setVisible(true);
		 setResizable(false);
		 //End of GUI Specifications
		 
		 
		 //Adding JPanels
		 add("South", southPanel);
		 //southPanel.setBackground(Color.WHITE);
		 southPanel.add(welcomeText);
		 //End of adding JPanels
		 

		 //Adding the Table
                 add(new JScrollPane(transactionsTable), BorderLayout.WEST);
                 add(button1, BorderLayout.EAST);
		 add(new JScrollPane(bookTable),BorderLayout.CENTER);
                 add(new JScrollPane(memberTable),BorderLayout.PAGE_START);
		 //End of adding the Table
                 
                 button1.addActionListener(this);
		 
		 
		 
		  this.addWindowListener(new WindowAdapter() {
			 
			 public void windowActivated(WindowEvent e) {
				 
				  loadDataBook();
                                  loadDataMember();
                                  loadTransactionsData();
			      }
                         
                          public void windowOpened(WindowEvent e) {
                                    loadDataBook();
                                    loadDataMember();
                                    loadTransactionsData();
    }
                         
                         
			 });
                  
                
   
		 
		 
}
    @Override
     public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==button1){
        addTransaction t1 = new addTransaction(); 
        
    }
    }
    
    
    
   
    
    
    public void loadDataBook(){
        conn=javaConnectDb.ConnecrDb();
        
        //Initializing the columns Names
        Object[] columnsName = new Object[7];
        
        columnsName[0] = "Book_id";
        columnsName[1] = "Name";
        columnsName[2] = "Author";
        columnsName[3] = "Edition";
        columnsName[4]="book_type_id";
        columnsName[5]="rack_id";
        columnsName[6]="date_of_purchase";
        
        model.setColumnIdentifiers(columnsName);
        //End of initializing the columns Names
        
        Object[] rowData = new Object[7];
     
        try{
            
        st = conn.createStatement();
	rs= st.executeQuery("select * from book");
        model.setRowCount(0);
			
			while(rs.next()){
				
				            rowData[0] = rs.getString("book_id");
				            rowData[1] = rs.getString("name");
				            rowData[2] = rs.getString("author");
				            rowData[3] = rs.getString("edition");
				            rowData[4] = rs.getString("book_type_id");
				            rowData[5] = rs.getString("rack_id");
				            rowData[6] = rs.getString("date_of_purchase");
				         
				               
				               model.addRow(rowData);
				
			
				
			}
        
        }
        
        	catch(Exception exc){
			
			exc.printStackTrace();
			
		}
       
       
    }
    
    
    public void loadDataMember(){
        conn=javaConnectDb.ConnecrDb();
        
        //Initializing the columns Names
        Object[] columnsName = new Object[5];
        
        columnsName[0] = "Member_id";
        columnsName[1] = "Name";
        columnsName[2] = "Address";
        columnsName[3] = "Phone_no";
        columnsName[4]="Member_type_id";

        
        model2.setColumnIdentifiers(columnsName);
        //End of initializing the columns Names
        
        Object[] rowData = new Object[5];
     
        try{
            
        st = conn.createStatement();
	rs= st.executeQuery("select * from member");
        model2.setRowCount(0);
			
			while(rs.next()){
				
				            rowData[0] = rs.getString("member_id");
				            rowData[1] = rs.getString("name");
				            rowData[2] = rs.getString("address");
				            rowData[3] = rs.getString("phone_no");
				            rowData[4] = rs.getString("member_type_id");

				         
				               
				               model2.addRow(rowData);
				
			
				
			}
        
        }
        
        	catch(Exception exc){
			
			exc.printStackTrace();
			
		}
       
       
    }
    
    
      public void loadTransactionsData(){
        conn=javaConnectDb.ConnecrDb();
        
        //Initializing the columns Names
        Object[] columnsName = new Object[6];
        
        columnsName[0] = "Transaction_id";
        columnsName[1] = "Member_id";
        columnsName[2] = "Book_id";
        columnsName[3] = "Issue_date";
        columnsName[4]="Due_date";
        columnsName[5]="Return_date";
   
        
        model3.setColumnIdentifiers(columnsName);
        //End of initializing the columns Names
        
        Object[] rowData = new Object[6];
     
        try{
            
        st = conn.createStatement();
	rs= st.executeQuery("select * from transactions");
        model3.setRowCount(0);
			
			while(rs.next()){
				
				            rowData[0] = rs.getString("transaction_id");
				            rowData[1] = rs.getString("member_id");
				            rowData[2] = rs.getString("book_id");
				            rowData[3] = rs.getString("issue_date");
				            rowData[4] = rs.getString("due_date");
				            rowData[5] = rs.getString("return_date");

				         
				               
				               model3.addRow(rowData);
				
			
				
			}
        
        }
        
        	catch(Exception exc){
			
			exc.printStackTrace();
			
		}
       
       
    }
    
    
    
    
}
