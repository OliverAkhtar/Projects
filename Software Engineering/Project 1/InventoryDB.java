import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InventoryDB
{
  public String dbms = "mysql";
  public String dbName = "InventoryDB"; 
  public String userName = "root";
  public String password = "root";
  public String urlString;
  
  private String serverName = "localhost";
  private int portNumber = 3306;
  
  public Connection getConnection() throws SQLException, ClassNotFoundException
  {
    String currentUrlString = "jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber 
    						+ "/";
    Connection conn = DriverManager.getConnection(currentUrlString, this.userName, this.password);
    this.urlString = currentUrlString + this.dbName;
    System.out.println("Connected to database");
    return conn;
  }
  
  public void CreateDatabase(Connection conn)
  {
      try
      {
        Statement s = conn.createStatement();
        String newDatabaseString = "CREATE DATABASE IF NOT EXISTS " + this.dbName;
        s.executeUpdate(newDatabaseString);
        conn.setCatalog(dbName);
      } 
      catch (SQLException e)
      {
        System.out.println(e.getMessage());
      }
  }
  
  public static void closeConnection(Connection conn)
  {
    try
    {
      if (conn != null)
      {
        conn.close();
        conn = null;
        System.out.println("Connection closed.");
      }
    }
    catch (SQLException sqle)
    {
      System.out.println(sqle.getMessage());
    }
  }
  
	
	public static void main(String[] args)
	{
		if(args.length != 2)
		{
			System.out.println("Input and output filenames not specificed at command line.");
			return;
		}
		
		Scanner inputFile;
		PrintWriter outputFile;
		InventoryDB myInventoryDB = new InventoryDB();
		Connection myConnection = null;
		try
		{
			inputFile = new Scanner(new File(args[0]));
			outputFile = new PrintWriter(args[1]);
			myConnection = myInventoryDB.getConnection();
			myInventoryDB.CreateDatabase(myConnection);
			
			ProductTable myProductTable = new ProductTable(myConnection);
			myProductTable.createTable();
			myProductTable.populateTable(inputFile, outputFile);
			
			MyGUI mg = new MyGUI(myInventoryDB, outputFile);
			mg.pack();
			mg.setVisible(true);
		}
		
		catch (SQLException e)
		{
      System.out.println(e.getMessage());
    }
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
		finally
		{
			InventoryDB.closeConnection(myConnection);
		}
	}
}
