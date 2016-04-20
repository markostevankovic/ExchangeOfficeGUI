package exchangeoffice.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;

import model.ExchangeRateTableModel;

/*
 * All code is generated MANUALY.
 * 
 * @author Marko Stevankovic
 */

public class ExchangeOffice extends JFrame implements ActionListener
{
	private JPanel panelCenter;
	private JPanel panelEast;
	private JPanel panelSouth;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenu separatorMenu;
	private JMenu helpMenu;
	
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemExit;
	private JMenuItem itemAbout;
	
	private JTable table;
	
	private JScrollPane scrollPane;
	
	private JPopupMenu popUpMenu;
	
	private JTextArea textArea;
	
	private JButton buttonAddExchangeRate;
	private JButton buttonRemoveExchangeRate;
	private JButton buttonExchange;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	public ExchangeOffice()
	{	
		setTitle("Exchange office");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelEast = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
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
		panelCenter.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT / 10));
		
		table = new JTable(new ExchangeRateTableModel());
		
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT / 10 - 10));
		
		popUpMenu = new JPopupMenu();
		
		popUpMenu.add(itemOpen);
		popUpMenu.add(itemSave);
		popUpMenu.add(itemExit);
		
		table.setComponentPopupMenu(popUpMenu);
		
		panelCenter.add(scrollPane);
		
		/*
		 * Adjusting south panel
		 */
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(WIDTH - 2, HEIGHT / 10));
		
		Border border = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(border, "STATUS");
		
		panelSouth.setBorder(titled);
	
		panelSouth.add(new JScrollPane(textArea));		

		/*
		 * Adjusting east panel
		 */
		panelEast.setPreferredSize(new Dimension(150, HEIGHT - HEIGHT/10));
		
		buttonAddExchangeRate = new JButton("Add ExRate");
		buttonRemoveExchangeRate = new JButton("Remove ExRate");
		buttonExchange= new JButton("Exchange");
		
		panelEast.add(buttonAddExchangeRate);
		panelEast.add(buttonRemoveExchangeRate);
		panelEast.add(buttonExchange);
		
		/**
		 * Defining listeners
		 */
		
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				closeTheApplication();
			}
		});
		
		/*
		 * Adding panels to frame...
		 */
		add(panelCenter, BorderLayout.CENTER);
		add(panelEast, BorderLayout.EAST);
		add(panelSouth, BorderLayout.SOUTH);
		
		pack();
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
	}

	/*
	 * Private methods for different actions
	 */
	private void showAbouDialog()
	{
		JOptionPane.showMessageDialog(new ExchangeOffice(),
				"Author: Marko Stevankovic", "About",
				JOptionPane.INFORMATION_MESSAGE);	
	}
	
	private void closeTheApplication() 
	{
		int option = JOptionPane.showConfirmDialog(new ExchangeOffice(),
				"Do you want the exit this application???", "Exit",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION)
			System.exit(0);	
	}
	
	private void showOpenDialog()
	{
		// TO DO
	}

	private void showSaveDialog()
	{
		try
		{
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showSaveDialog(this);

			if (option == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();

				//SOUpisi.upisiUFajl(file.getAbsolutePath(), menjacnica.vratiKursnuListu());
				
			}
		}
		catch (Exception exc) 
		{
			JOptionPane.showMessageDialog(this, exc.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
