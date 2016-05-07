package exchangeoffice.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exchangeoffice.Currency;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionGUI extends JFrame implements ActionListener, ChangeListener
{
	private Currency currency;
	
	private JPanel panel;
	
	private JLabel lbBuying;
	private JLabel lbSelling;
	private JLabel lbAmount;
	private JLabel lbTransactionType;
	
	private final ButtonGroup buttonGroup;
	
	private JComboBox comboBox;
	
	private JSlider slider;
	
	private JTextField tfBuyingRate;
	private JTextField tfSellingRate;
	private JTextField tfAmount;
	
	private JRadioButton jradioBuying;
	private JRadioButton jradioSelling;
	
	private JButton buttonTransaction;
	private JButton buttonClose;
	
	public TransactionGUI(Currency currency)
	{
		this.currency = currency;
		
		setTitle("Perform transaction");
		setSize(480, 340);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExchangeOffice.class.getResource("/icons/adriana.jpg")));
		
		panel = new JPanel();
		panel.setLayout(null);
		
		lbBuying = new JLabel("Buying rate");
		lbBuying.setBounds(10, 20, 90, 25);
		
		lbSelling = new JLabel("Selling rate");
		lbSelling.setBounds(280, 20, 90, 25);
		
		lbAmount = new JLabel("Amount");
		lbAmount.setBounds(10, 80, 90, 25);
		
		lbTransactionType = new JLabel("Transaction type");
		lbTransactionType.setBounds(280, 80, 200, 25);
		
		comboBox = new JComboBox();
		comboBox.addItem("EUR");
		comboBox.addItem("USD");
		comboBox.addItem("CHF");
		comboBox.setBounds(185, 40, 80, 30);
		
		buttonGroup = new ButtonGroup();
		
		tfSellingRate = new JTextField();
		tfSellingRate.setEnabled(false);
		tfSellingRate.setBackground(Color.lightGray);
		tfSellingRate.setBounds(280, 40, 160, 30);
		tfSellingRate.setColumns(10);
		
		slider = new JSlider();
		
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setBounds(10, 180, 450, 50);
		
		slider.addChangeListener(this);
		
		
		tfAmount = new JTextField();
		tfAmount.setBounds(10, 100, 160, 30);
		tfAmount.setColumns(15);
		tfAmount.setText("" + slider.getValue());
		
		tfBuyingRate = new JTextField();
		tfBuyingRate.setEnabled(false);
		tfBuyingRate.setBackground(Color.lightGray);
		tfBuyingRate.setBounds(10, 40, 160, 30);
		tfBuyingRate.setColumns(10);
		
		jradioBuying = new JRadioButton("Buying");
		buttonGroup.add(jradioBuying);
		jradioBuying.setSelected(true);
		jradioBuying.setBounds(280, 100, 150, 25);
		
		jradioSelling = new JRadioButton("Selling");
		buttonGroup.add(jradioSelling);
		jradioSelling.setBounds(280, 120, 150, 25);
		
		buttonClose = new JButton("Close");
		buttonClose.setBounds(320, 235, 120, 25);
		
		buttonTransaction = new JButton("Transact");
		buttonTransaction.setBounds(25, 235, 120, 25);
		
		buttonClose.addActionListener(this);
		buttonTransaction.addActionListener(this);
		
		panel.add(lbBuying);
		panel.add(lbSelling);
		panel.add(tfBuyingRate);
		panel.add(comboBox);
		panel.add(tfSellingRate);
		panel.add(lbAmount);
		panel.add(lbTransactionType);
		panel.add(tfAmount);
		panel.add(jradioBuying);
		panel.add(jradioSelling);
		panel.add(slider);
		panel.add(buttonTransaction);
		panel.add(buttonClose);
		
		add(panel);
		
		//pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		
		if(source == buttonClose)
			this.dispose();
		
		else if(source == buttonTransaction)
			performTransaction();
		
	}

	private void performTransaction() 
	{
		try
		{
			double transaction;
			boolean buying = false;
			String transactionType;
			
			if(jradioBuying.isSelected())
			{
				transaction = Double.parseDouble(tfAmount.getText()) * currency.getBuying(); 
				buying = true;
			}
			else
				transaction = Double.parseDouble(tfAmount.getText()) * currency.getSelling(); 
			
			tfAmount.setText("" + transaction);
			tfAmount.setEnabled(false);
			
			if(buying)
				transactionType = "buying";
			else
				transactionType = "selling";
			
			String message = 
							"TRANSACTION : " 
							+ "TO currency(" 
							+ comboBox.getSelectedItem() 
							+ "), TYPE(" 
							+ transactionType 
							+ ")" 
							+ ", TOTAL AMOUNT(" + Double.parseDouble(tfAmount.getText()) + ")";
			
			ExchangeOffice.appendTextToTextArea(message);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(
					this,
					"Something went wrong in transaction proces...",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) 
	{
		tfAmount.setText("" + slider.getValue());
	}
}
