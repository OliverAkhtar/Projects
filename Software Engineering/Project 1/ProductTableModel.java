import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ProductTableModel implements TableModel
{
	CachedRowSet productRowSet;
	ResultSetMetaData metadata;
	int numCols, numRows;

	public ProductTableModel(CachedRowSet rowSetArg) throws SQLException
	{
		productRowSet = rowSetArg;
		metadata = productRowSet.getMetaData();
		numCols = metadata.getColumnCount();
		
		productRowSet.beforeFirst();
		numRows = 0;
		while(productRowSet.next())
			numRows++;
		productRowSet.beforeFirst();
	}

	public CachedRowSet getProductRowSet() {
		return productRowSet;
	}
	
	public void addEventHandlersToRowSet(RowSetListener listener)
	{
		productRowSet.addRowSetListener(listener);
	}
	
	public void insertRow(String productName, float price, int qty, int thresh, PrintWriter output)
	{
		try
		{
			productRowSet.moveToInsertRow();
		    productRowSet.updateString("product_name", productName);
		    productRowSet.updateFloat("price", price);
		    productRowSet.updateInt("quantity", qty);
		    productRowSet.updateInt("threshold", thresh);
		    productRowSet.insertRow();
		    productRowSet.moveToCurrentRow();
		    output.println("INSERT into Product (product_name, price, quantity, threshold) " +
									 "values(" + productName + ", " + price + ", " + qty + ", " + thresh + ")");
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void updateRow(String productName, float newPrice, int newQTY, int newThresh, PrintWriter output)
	{
		try
		{
			productRowSet.beforeFirst();
			while(productRowSet.next())
			{
				if(productRowSet.getString("product_name").equals(productName))
				{
					productRowSet.updateFloat("price", newPrice);
					productRowSet.updateInt("quantity", newQTY);
					productRowSet.updateInt("threshold", newThresh);
					productRowSet.updateRow();
					output.println("UPDATE Product SET price=" + newPrice + ", quantity=" + newQTY + ", threshold=" + newThresh +
												 " WHERE product_name='" + productName + "' " + new Date());
					break;
				}
			}
			productRowSet.beforeFirst();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteRow(String productName, PrintWriter output)
	{
		try
		{
			productRowSet.beforeFirst();
			while(productRowSet.next())
			{
				if(productRowSet.getString("product_name").equals(productName))
				{
					productRowSet.deleteRow();
					output.println("DELETE FROM PRODUCT WHERE product_name='" + productName + "' " + new Date());
					break;
				}
			}
			productRowSet.beforeFirst();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void close()
	{
		try
		{
			productRowSet.getStatement().close();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/** Method from interface TableModel; returns the number of columns */

  public int getColumnCount() {
    return numCols;
  }

    /** Method from interface TableModel; returns the number of rows */

  public int getRowCount() {
    return numRows;
  }

  /** Method from interface TableModel; returns the column name at columnIndex
   *  based on information from ResultSetMetaData
   */

  public String getColumnName(int column) {
    try {
      return this.metadata.getColumnLabel(column + 1);
    } catch (SQLException e) {
      return e.toString();
    }
  }

  /** Method from interface TableModel; returns the most specific superclass for
   *  all cell values in the specified column. To keep things simple, all data
   *  in the table are converted to String objects; hence, this method returns
   *  the String class.
   */

  public Class getColumnClass(int column) {
    return String.class;
  }

  /** Method from interface TableModel; returns the value for the cell specified
   *  by columnIndex and rowIndex. TableModel uses this method to populate
   *  itself with data from the row set. SQL starts numbering its rows and
   *  columns at 1, but TableModel starts at 0.
   */

  public Object getValueAt(int rowIndex, int columnIndex) {

    try {
      productRowSet.absolute(rowIndex + 1);
      Object o = productRowSet.getObject(columnIndex + 1);
      if (o == null)
        return null;
      else
        return o.toString();
    } catch (SQLException e) {
      return e.toString();
    }
  }

    /** Method from interface TableModel; returns true if the specified cell
     *  is editable. This sample does not allow users to edit any cells from
     *  the TableModel (rows are added by another window control). Thus,
     *  this method returns false.
     */

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  // Because the sample does not allow users to edit any cells from the
  // TableModel, the following methods, setValueAt, addTableModelListener,
  // and removeTableModelListener, do not need to be implemented.

  public void setValueAt(Object value, int row, int column) {
    System.out.println("Calling setValueAt row " + row + ", column " + column);
  }

  public void addTableModelListener(TableModelListener l) {
  }

  public void removeTableModelListener(TableModelListener l) {
  }

  

}
