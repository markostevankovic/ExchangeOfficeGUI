package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import exchangeoffice.Currency;

public class ExchangeRateTableModel extends AbstractTableModel
{
	String[] columnNames = {"ID", "Abbreviation", "Selling", "Middle", "Buying", "Name"};
	List<Currency> currencies;
	
	public ExchangeRateTableModel(List<Currency> currencies) 
	{
		this.currencies = currencies;
	}
	
	@Override
	public int getColumnCount()
	{
		return 6;
	}

	@Override
	public int getRowCount() 
	{
		return currencies.size();
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
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		Currency tmp = currencies.get(rowIndex);
		
		switch(columnIndex)
		{
			case 0: tmp.setId((int)aValue);
			case 1: tmp.setSymbol((String)aValue);
			case 2: tmp.setName((String)aValue);
			case 3: tmp.setBuying((double)aValue);
			case 4: tmp.setSelling((double)aValue);
		}
		
		// fireTableDataChanged();
	}
	
	public void addCurrency(Currency newCurrency)
	{
		currencies.add(newCurrency);
		fireTableDataChanged();
	}
	
	public void removeCurrency(int row)
	{
		currencies.remove(row);
		fireTableDataChanged();
	}
}
