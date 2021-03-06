package exchangeoffice.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import exchangeoffice.Currency;
import exchangeoffice.ExchangeOfice;
import model.ExchangeRateTableModel;

/*
 * All code is generated MANUALY.
 * 
 * @author Marko Stevankovic
 */

public class ExchangeOffice extends JFrame implements ActionListener
{
	// private JPanel panelCenter;
	private JPanel panelEast;
	// private JPanel panelSouth;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenu separatorMenu;
	private JMenu helpMenu;
	
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemExit;
	private JMenuItem itemAbout;
	
	private static JTable table;
	
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneTextArea;
	
	private JPopupMenu popUpMenu;
	
	private static JTextArea textArea;
	
	private JButton buttonAddExchangeRate;
	private JButton buttonRemoveExchangeRate;
	private JButton buttonExchange;
	
	private JMenuItem itemAdd;
	private JMenuItem itemRemove;
	private JMenuItem itemExchange;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	public ExchangeOffice()
	{	
		/**
		 * Defining listeners
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeTheApplication();
			}
		});
		setTitle("Exchange office");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExchangeOffice.class.getResource("/icons/jessica.jpg")));
		
		// panelCenter = new JPanel(new BorderLayout());
		panelEast = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// panelSouth = new JPanel(new BorderLayout());
		
		/*
		 * Adjusting menu bar
		 */
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		separatorMenu = new JMenu("|");
		helpMenu = new JMenu("Help");
		
		separatorMenu.setEnabled(false);
		
		itemOpen = new JMenuItem("Open");
		itemSave = new JMenuItem("Save");
		itemExit = new JMenuItem("Exit");
		
		itemOpen.setIcon(new ImageIcon(ExchangeOffice.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		itemSave.setIcon(new ImageIcon(ExchangeOffice.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, InputEvent.CTRL_MASK));
		itemSave.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, InputEvent.CTRL_MASK));
		itemExit.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_X, InputEvent.ALT_MASK));
		
		itemExit.addActionListener(this);
		itemSave.addActionListener(this);
		itemOpen.addActionListener(this);
		
		itemAbout = new JMenuItem("About");
		
		itemAbout.addActionListener(this);
		
		fileMenu.add(itemOpen);
		fileMenu.add(itemSave);
		fileMenu.add(itemExit);
		
		helpMenu.add(itemAbout);
		
		menuBar.add(fileMenu);
		menuBar.add(separatorMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		/*
		 * Adjusting central panel
		 */
		// panelCenter.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT / 10));
		
		table = new JTable(new ExchangeRateTableModel());
		
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT / 10 - 10));
		
		itemAdd = new JMenuItem("Add new currency");
		itemRemove = new JMenuItem("Remove currency");
		itemExchange = new JMenuItem("Exchange");
		
		itemAdd.addActionListener(this);
		itemRemove.addActionListener(this);
		itemExchange.addActionListener(this);
		
		popUpMenu = new JPopupMenu();
		
		popUpMenu.add(itemAdd);
		popUpMenu.add(itemRemove);
		popUpMenu.add(itemExchange);
		
		table.setComponentPopupMenu(popUpMenu);
		
		// panelCenter.add(scrollPane, BorderLayout.CENTER);		
		/*
		 * Adjusting south panel
		 */
		
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(WIDTH - 2, HEIGHT / 10));
		
		scrollPaneTextArea = new JScrollPane(textArea);
		// scrollPaneTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		Border border = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(border, "STATUS");
		
		scrollPaneTextArea.setBorder(titled);
		
		// panelSouth.setBorder(titled);
		// panelSouth.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		/*
		 * Adjusting east panel
		 */
		panelEast.setPreferredSize(new Dimension(150, HEIGHT - HEIGHT/10));
		
		buttonAddExchangeRate = new JButton("Add ExRate");
		buttonRemoveExchangeRate = new JButton("Remove ExRate");
		buttonExchange= new JButton("Exchange");
		
		buttonAddExchangeRate.addActionListener(this);
		buttonRemoveExchangeRate.addActionListener(this);
		buttonExchange.addActionListener(this);
		
		panelEast.add(buttonAddExchangeRate);
		panelEast.add(buttonRemoveExchangeRate);
		panelEast.add(buttonExchange);
		
		/*
		 * Adding panels to frame...
		 */
		
		add(scrollPane, BorderLayout.CENTER);
		add(panelEast, BorderLayout.EAST);
		add(scrollPaneTextArea, BorderLayout.SOUTH);
		// add(panelCenter, BorderLayout.CENTER);
		// add(panelSouth, BorderLayout.SOUTH);
		
		pack();
	}

	/*
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == itemAbout)
			showAbouDialog();
		
		else if(source == itemExit)
			closeTheApplication();
		
		else if(source == itemSave)
			showSaveDialog();
		
		else if(source == itemOpen)
			showOpenDialog();
		
		else if(source == itemAdd || source == buttonAddExchangeRate)
			showAddNewExchangeRateFrame();
		
		else if(source == itemRemove || source == buttonRemoveExchangeRate)
			removeSelectedCurrency();
		
		else if(source == itemExchange || source == buttonExchange)
			showTransactionGUI();
	}

	/*
	 * Method which shows message dialog,
	 * which contains informations about author
	 */
	private void showAbouDialog()
	{
		JOptionPane.showMessageDialog(
				new ExchangeOffice(),
				"Author: Marko Stevankovic",
				"About",
				JOptionPane.INFORMATION_MESSAGE);	
	}
	
	/*
	 * Method which creates confirm dialog in which we choose
	 * whether we want to close our application.
	 */
	private void closeTheApplication() 
	{
		int option = JOptionPane.showConfirmDialog(
				new ExchangeOffice(),
				"Quit this application???",
				"Exit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION)
			System.exit(0);	
	}
	
	/*
	 * Method which creates a new open dialog.
	 * We choose a file from which we want to read
	 * list of currencies.
	 * The goal is to replace our global list of currencies
	 * with loaded list.
	 */
	private void showOpenDialog()
	{
		try 
		{
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(this);

			if (option == JFileChooser.APPROVE_OPTION) 
			{
				File file = fileChooser.getSelectedFile();

				readFromAFile(file.getAbsolutePath());

				textArea.append("Loaded file: " + file.getAbsolutePath() + "\n");
			}	
		} 
		catch (Exception exc) 
		{
			JOptionPane.showMessageDialog(
					this,
					exc.getMessage(),
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * Method which replaces our global list of currencies
	 * with a list of currencies loaded from a choosen file.
	 */
	private void readFromAFile(String absolutePath)
	{
		ObjectInputStream in = null;
		
		try
		{
			in = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(absolutePath)));
			
			ArrayList<Currency> list = (ArrayList<Currency>)(in.readObject());
			
			ExchangeOfice.getInstanceOfExchangeOffice().getAllCurrencies().clear();
			
			ExchangeOfice.getInstanceOfExchangeOffice().getAllCurrencies().addAll(list);
			
			updateTable();
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	/*
	 * Method which creates new save dialog.
	 */
	private void showSaveDialog()
	{
		try
		{
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showSaveDialog(this);

			if (option == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				
				saveToAFile(file.getAbsolutePath(), ExchangeOfice.getInstanceOfExchangeOffice().getAllCurrencies());
				
				textArea.append("File saved: " + file.getAbsolutePath() + "\n");
			}
		}
		catch (Exception exc) 
		{
			JOptionPane.showMessageDialog(
					this,
					exc.getMessage(),
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 * Method which saves our global list of currencies
	 * into a file, to a choosen location.
	 */
	private void saveToAFile(String absolutePath, ArrayList<Currency> currencies) 
	{
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(absolutePath)));
			
			out.writeObject(currencies);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Method which creates a new frame
	 * for adding new currency to our global list of currencies
	 */
	private void showAddNewExchangeRateFrame()
	{
		AddExchangeRateGUI newFrame = new AddExchangeRateGUI();
		newFrame.setVisible(true);
		newFrame.setLocationRelativeTo(this);
	}
	
	/*
	 * static methods for updating the content of a table
	 */
	public static void updateTable()
	{
		ExchangeRateTableModel tableModel = (ExchangeRateTableModel)table.getModel();
		tableModel.updateTable();
	}
	
	/*
	 * Static method for appending text to textArea component
	 * from another dialogs
	 */
	public static void appendTextToTextArea(String text)
	{
		textArea.append(text + "\n");
	}
	
	/*
	 * Method which removes selected row
	 */
	private void removeSelectedCurrency() 
	{
		try
		{
			int selectedRow = table.getSelectedRow();
			
			ExchangeOfice office = ExchangeOfice.getInstanceOfExchangeOffice();
			
			Currency currency = office.getAllCurrencies().get(selectedRow);
			
			int dialogResult = JOptionPane.showConfirmDialog (
					null, 
					"Would You Like to remove selected row? "
					+ "\n(row index: " + selectedRow + ")",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				try
				{
					office.removeCurrency(currency);
					
					ExchangeRateTableModel tableModel = (ExchangeRateTableModel)table.getModel();
					tableModel.updateTable();
					
					appendTextToTextArea("DELETED: row with index->" + selectedRow);
					
					JOptionPane.showMessageDialog(
							null,
							"Succesfully removed selected row!",
							"COMMAND COMMITED",
							JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(
							this,
							"Something went wrong!\nSelected row could not be deleted!",
							"ERROR", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(
					this,
					"You have to choose/selected one ROW",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * Method which opens up TransatctionGUI frame
	 */
	private void showTransactionGUI() 
	{
		try
		{
			int selectedRow = table.getSelectedRow();
			
			ExchangeOfice office = ExchangeOfice.getInstanceOfExchangeOffice();
			
			Currency currency = office.getAllCurrencies().get(selectedRow);
			
			TransactionGUI frame = new TransactionGUI(currency);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(
					this,
					"You have to choose/selected one ROW!!!",
					"ERROR", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
