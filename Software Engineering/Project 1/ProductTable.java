import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProductTable
{
	private Connection con;
	
	public ProductTable(Connection conArg)
	{
		con = conArg;
	}
	
	public void createTable() throws SQLException
	{
		String createString =
	      "create table if not exists Product (" + 
	      "P_Id int NOT NULL AUTO_INCREMENT, " + 
	      "product_name varchar(32) NOT NULL, " +
	      "price decimal(4,2) NOT NULL, " + 
	      "quantity int NOT NULL, " +
	      "threshold int NOT NULL, " +
	      "PRIMARY KEY (P_Id)" +
	      ")";
	    Statement stmt = null;
	    try
	    {
	      stmt = con.createStatement();
	      stmt.executeUpdate(createString);
	    }
	    catch (SQLException e)
	    {
	      System.out.println(e.getMessage());
	    }
	    finally
	    {
	      if (stmt != null) { stmt.close(); }
	    }
	}
	
	public void populateTable(Scanner input, PrintWriter output) throws SQLException
	{
    Statement stmt = null;
    try
    {
      stmt = con.createStatement();
      String line;
      StringTokenizer st;
      String productName, price, quantity, threshold;
      while(input.hasNextLine())
      {
      	line = input.nextLine();
      	st = new StringTokenizer(line, ",");
      	productName = st.nextToken();
      	price = st.nextToken();
      	quantity = st.nextToken();
      	threshold = st.nextToken();
      	stmt.executeUpdate("insert into Product (product_name, price, quantity, threshold) " +
						   "values('" + productName + "', " + price + ", " + quantity + ", " + threshold + ")");
      	output.println("INSERT into Product (product_name, price, quantity, threshold) " +
					   "values('" + productName + "', " + price + ", " + quantity + ", " + threshold + ") " + new Date());
      }
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
    }
    finally
    {
      if (stmt != null) { stmt.close(); }
    }
  }
}
