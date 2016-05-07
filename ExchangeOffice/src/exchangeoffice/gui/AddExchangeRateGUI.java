package exchangeoffice.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exchangeoffice.Currency;
import exchangeoffice.ExchangeOfice;

public class AddExchangeRateGUI extends JFrame implements ActionListener
{	
	private JLabel lbID;
	private JLabel lbName;
	private JLabel lbSellingRate;
	private JLabel lbBuyingRate;
	private JLabel lbMiddleRate;
	private JLabel lbAbbreviation;
	
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfSellingRate;
	private JTextField tfBuyingRate;
	private JTextField tfMiddleRate;
	private JTextField tfAbbreviation;
	
	private JPanel panel;
	
	private JButton buttonAdd;
	private JButton buttonCancel;
	
	public AddExchangeRateGUI()
	{
		setTitle("Add exchange rate");
		setSize(400, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExchangeOffice.class.getResource("/icons/adriana.jpg")));
		
		panel = new JPanel(new GridLayout(7, 2, 5, 5));
		
		lbID = new JLabel("ID");
		lbName = new JLabel("Currency");
		lbBuyingRate = new JLabel("Buying rate");
		lbSellingRate = new JLabel("Selling rate");
		lbMiddleRate = new JLabel("Middle rate");
		lbAbbreviation = new JLabel("Symbol");
		
		tfID = new JTextField();
		tfName = new JTextField();
		tfSellingRate = new JTextField();
		tfBuyingRate = new JTextField();
		tfMiddleRate = new JTextField();
		tfAbbreviation = new JTextField();
		
		tfMiddleRate.setEnabled(false);
		tfMiddleRate.setBackground(Color.GRAY);
		
		buttonAdd = new JButton("Add");
		buttonCancel = new JButton("Cancel");
		
		buttonAdd.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		panel.add(lbID);
		panel.add(lbName);
		panel.add(tfID);
		panel.add(tfName);
		panel.add(lbSellingRate);
		panel.add(lbBuyingRate);
		panel.add(tfSellingRate);
		panel.add(tfBuyingRate);
		panel.add(lbMiddleRate);
		panel.add(lbAbbreviation);
		panel.add(tfMiddleRate);
		panel.add(tfAbbreviation);
		panel.add(buttonAdd);
		panel.add(buttonCancel);	
		
		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		
		if(source == buttonCancel)
			closeAddExchangeRateGUI();
		
		else if(source == buttonAdd)
			addNewExchangeRateOrCurrency(Integer.parseInt(tfID.getText()), tfName.getText(), Double.parseDouble(tfSellingRate.getText()), Double.parseDouble(tfBuyingRate.getText()), tfAbbreviation.getText());
	}

	private void closeAddExchangeRateGUI() 
	{
		this.dispose();	
	}

	private void addNewExchangeRateOrCurrency(int id, String currencyName, double sellingRate, double buyingRate, String currencySymbol) 
	{
		try
		{
			Currency newCurrency = new Currency(id, currencySymbol, currencyName, buyingRate, sellingRate);
		
			ExchangeOfice office = ExchangeOfice.getInstanceOfExchangeOffice();
			
			office.addNewCurrency(newCurrency);
			
			ExchangeOffice.updateTable();
			
			String message = "ADDED CURRENCY: " + id + ", " + currencyName + ", " + sellingRate + ", " + buyingRate + ", " + currencySymbol; 
			
			ExchangeOffice.appendTextToTextArea(message);
			
			refreshTextFields();
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(
					null, 
					exc.getMessage(),
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refreshTextFields() 
	{
		tfID.setText(null);
		tfAbbreviation.setText(null);
		tfBuyingRate.setText(null);
		tfName.setText(null);
		tfSellingRate.setText(null);
	}
}
