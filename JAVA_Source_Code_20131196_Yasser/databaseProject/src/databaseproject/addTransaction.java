
package databaseproject;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author yasse
 */
public class addTransaction extends JFrame implements ActionListener {

    
	//initializing the labels
	JLabel memberIdLabel = new JLabel("  Member ID: ");
	JLabel bookIdLabel = new JLabel("  Book ID: ");
	JLabel issueDateLabel = new JLabel(" Issue Date (DD-MON-YY): ");
	JLabel dueDateLabel = new JLabel("  Due Date ");
	//End of initializing labels
	
	//initializing the text fields
	JTextField memberIdText= new JTextField(20);
	JTextField bookIdText= new JTextField(20);
	JTextField issueDateText = new JTextField(20);
	JTextField dueDateText= new JTextField(20);
	//End of initializing labels
	
	//initializing the buttons
	JButton submit = new JButton("Submit");
	JButton reset = new JButton("Clear");
	//End of initializing buttons
        
      
    
    public addTransaction(){
		
		setTitle("Adding a new Transaction");
		setLayout(new GridLayout(8,2,10,10));
		setSize(640,480);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
	
		add(memberIdLabel);
		add(memberIdText);
		add(bookIdLabel);
		add(bookIdText);
		add(issueDateLabel);
		add(issueDateText);
		add(dueDateLabel);
		add(dueDateText);
		add(submit);
		add(reset);
		submit.addActionListener(this);
		reset.addActionListener(this);
		
		
	}
	
    
    
    
    
    
    
    @Override
    
	public void actionPerformed(ActionEvent arg0) {
		

		//TODO add error checker
		
		if (arg0.getSource()==submit){
			
                        
			if(!( (memberIdText.getText().equals("")) || (bookIdText.getText().equals("")) || (issueDateText.getText().equals("")) || (dueDateText.getText().equals("")))){
                            try {
                                insertTransaction();
                            } catch (SQLException ex) {
                                Logger.getLogger(addTransaction.class.getName()).log(Level.SEVERE, null, ex);
                            }
			dispose();
			}
			else{
				JOptionPane.showMessageDialog(null,"You have to fill all the required fields!","Warning",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (arg0.getSource()==reset){
			
			memberIdText.setText("");
			bookIdText.setText("");
			issueDateText.setText("");
			dueDateText.setText("");
			
			
		}
		
	}
        
        
 public void insertTransaction() throws SQLException{
     
     	//Connecting to the database
        int member_id= Integer.parseInt(memberIdText.getText());
        int book_id= Integer.parseInt(bookIdText.getText());
        
        Connection conn = null;
        conn=javaConnectDb.ConnecrDb();
        //CallableStatement st = conn.prepareCall ("{call borrow_a_book (?, ?,?,?)}");
         CallableStatement st = conn.prepareCall ("begin borrow_a_book (?,?,?,?); end;");
        //End of database connection
	
try{
                st.setInt(1, member_id);
                st.setInt(2, book_id);
                st.setString(3, issueDateText.getText());
                st.setString(4, dueDateText.getText());
                
		st.execute();
		
		
	}

	
	catch(Exception exc){
		
		exc.printStackTrace();
                JOptionPane.showMessageDialog(null,"An error occured!");
		
	}
     
     
 }
    
}
