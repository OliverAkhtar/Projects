import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.rowset.CachedRowSetImpl;

public class MyGUI extends JFrame implements RowSetListener
{
  InventoryDB settings;
  Connection connection;
  JTable table;
	
  JLabel label_PRODUCT_NAME;
  JLabel label_PRICE;
  JLabel label_QTY;
  JLabel label_THRESHOLD;

  JTextField textField_PRODUCT_NAME;
  JTextField textField_PRICE;
  JTextField textField_QTY;
  JTextField textField_THRESHOLD;

  JButton button_ADD_ROW;
  JButton button_UPDATE_ROW;
  JButton button_DELETE_ROW;
  JButton button_QUERY;
  
  ProductTableModel myProductTableModel;
  
  PrintWriter logfile;
  
  public MyGUI(InventoryDB settingsArg, PrintWriter output) throws ClassNotFoundException, SQLException
  {
  	super("InventoryDB: Product Table");
  	
  	settings = settingsArg;
  	connection = settings.getConnection();
  	logfile = output;
  	
  	addWindowListener(new WindowAdapter() {
  		public void windowClosing(WindowEvent e)
  		{
  			try
  			{
  				connection.close();
  				logfile.close();
  			}
  			catch(SQLException sqle)
  			{
  				System.out.println(sqle.getMessage());
  			}
  			System.exit(0);
  		}
  	});
  	
  	CachedRowSet myCachedRowSet = getContentsOfProductTable();
  	myProductTableModel = new ProductTableModel(myCachedRowSet);
  	myProductTableModel.addEventHandlersToRowSet(this);
  	
  	table = new JTable();
  	table.setModel(myProductTableModel);
  	
  	label_PRODUCT_NAME = new JLabel();
    label_PRICE = new JLabel();
    label_QTY = new JLabel();
    label_THRESHOLD = new JLabel();

    textField_PRODUCT_NAME = new JTextField(10);
    textField_PRICE = new JTextField(10);
    textField_QTY = new JTextField(10);
    textField_THRESHOLD = new JTextField(10);

    button_ADD_ROW = new JButton();
    button_UPDATE_ROW = new JButton();
    button_DELETE_ROW = new JButton();
    button_QUERY = new JButton();
    
    label_PRODUCT_NAME.setText("Product Name:");
    label_PRICE.setText("Price:");
    label_QTY.setText("Quantity:");
    label_THRESHOLD.setText("Threshold:");

    textField_PRODUCT_NAME.setText("Enter new product name");
    textField_PRICE.setText("0");
    textField_QTY.setText("0");
    textField_THRESHOLD.setText("0");

    button_ADD_ROW.setText("Add row to table");
    button_UPDATE_ROW.setText("Update row via product name");
    button_DELETE_ROW.setText("Delete row via product name");
    button_QUERY.setText("Show products less than entered price");
    
    Container contentPane = getContentPane();
    contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    contentPane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    c.fill = GridBagConstraints.BOTH;
    c.anchor = GridBagConstraints.CENTER;
    c.weightx = 0.5;
    c.weighty = 1.0;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    contentPane.add(new JScrollPane(table), c);
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.25;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 1;
    contentPane.add(label_PRODUCT_NAME, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 1;
    c.gridwidth = 1;
    contentPane.add(textField_PRODUCT_NAME, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.25;
    c.weighty = 0;
    c.anchor = GridBagConstraints.LINE_START;
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 1;
    contentPane.add(label_PRICE, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 1;
    contentPane.add(textField_PRICE, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.25;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 1;
    contentPane.add(label_QTY, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 3;
    c.gridwidth = 1;
    contentPane.add(textField_QTY, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.25;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 1;
    contentPane.add(label_THRESHOLD, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 4;
    c.gridwidth = 1;
    contentPane.add(textField_THRESHOLD, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 5;
    c.gridwidth = 1;
    contentPane.add(button_ADD_ROW, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 5;
    c.gridwidth = 1;
    contentPane.add(button_UPDATE_ROW, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 6;
    c.gridwidth = 1;
    contentPane.add(button_DELETE_ROW, c);
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 6;
    c.gridwidth = 1;
    contentPane.add(button_QUERY, c);
    
    button_ADD_ROW.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			myProductTableModel.insertRow(textField_PRODUCT_NAME.getText(), 
											  Float.parseFloat(textField_PRICE.getText().trim()), 
											  Integer.parseInt(textField_QTY.getText().trim()), 
											  Integer.parseInt(textField_THRESHOLD.getText().trim()),
											  logfile);
    			myProductTableModel.productRowSet.acceptChanges();
    			createNewTableModel();
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    });
    
    button_UPDATE_ROW.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			myProductTableModel.updateRow(textField_PRODUCT_NAME.getText(), 
											  Float.parseFloat(textField_PRICE.getText().trim()), 
											  Integer.parseInt(textField_QTY.getText().trim()), 
											  Integer.parseInt(textField_THRESHOLD.getText().trim()),
											  logfile);
    			myProductTableModel.productRowSet.acceptChanges();
    			createNewTableModel();
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    });
    
    button_DELETE_ROW.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			myProductTableModel.deleteRow(textField_PRODUCT_NAME.getText(),
    										  logfile);
    			myProductTableModel.productRowSet.acceptChanges();
    			createNewTableModel();
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    });
    
    button_QUERY.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			queryProductTable(Float.parseFloat(textField_PRICE.getText()));
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    	}
    });
    
  }
  
  private void createNewTableModel() throws SQLException, ClassNotFoundException
  {
    myProductTableModel = new ProductTableModel(getContentsOfProductTable());
    myProductTableModel.addEventHandlersToRowSet(this);
    table.setModel(myProductTableModel);
  }

	public CachedRowSet getContentsOfProductTable() throws ClassNotFoundException
	{
		CachedRowSet crs = null;
		try
		{
			connection = settings.getConnection();
			crs = new CachedRowSetImpl();
			crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
			crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
			crs.setUsername(settings.userName);
			crs.setPassword(settings.password);
			crs.setUrl(settings.urlString + "?relaxAutoCommit=true");
			crs.setCommand("select product_name, price, quantity, threshold from Product");
			crs.execute();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return crs;
	}
	
	public void queryProductTable(Float price) throws ClassNotFoundException, SQLException
	{
		CachedRowSet crs = null;
		try
		{
			connection = settings.getConnection();
			crs = new CachedRowSetImpl();
			crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
			crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
			crs.setUsername(settings.userName);
			crs.setPassword(settings.password);
			crs.setUrl(settings.urlString + "?relaxAutoCommit=true");
			crs.setCommand("select product_name, price, quantity, threshold from Product " +
					 	   "where price<" + price);
			crs.execute();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		myProductTableModel = new ProductTableModel(crs);
		myProductTableModel.addEventHandlersToRowSet(this);
		table.setModel(myProductTableModel);
		logfile.println("SELECT product_name, price, quantity, threshold from Product WHERE price<" + price + 
						" " + new Date());
	}

	@Override
	public void rowSetChanged(RowSetEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
