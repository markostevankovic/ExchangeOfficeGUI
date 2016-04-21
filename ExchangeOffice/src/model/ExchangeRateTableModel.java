package model;

import javax.swing.table.AbstractTableModel;

public class ExchangeRateTableModel extends AbstractTableModel
{
	String[] columnNames = {"ID", "Abbreviation", "Selling", "Middle", "Buying", "Name"};
	
	/*
	 * 1) add List attribute which will populate rows of table
	 * 2) add constructor which takes list of currencies as it's input parameter
	 */
	
	@Override
	public int getColumnCount()
	{
		return 6;
	}

	@Override
	public int getRowCount() 
	{
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{
		return false;
	}
	
	@Override
	public String getColumnName(int column) 
	{
		switch(column)
		{
			case 0: return columnNames[0];
			case 1: return columnNames[1];
			case 2: return columnNames[2];
			case 3: return columnNames[3];
			case 4: return columnNames[4];
			case 5: return columnNames[5];
			default: return "N/A";
		}	
	}
}
