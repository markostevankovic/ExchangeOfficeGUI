package exchangeoffice.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import model.ExchangeRateTableModel;

/*
 * @author Marko Stevankovic
 */

public class ExchangeOffice extends JFrame
{
	private JPanel panelCenter;
	private JPanel panelEast;
	private JPanel panelSouth;
	
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
		 * Adjusting east panel
		 */
		panelEast.setPreferredSize(new Dimension(150, HEIGHT - HEIGHT/10));
		
		JButton buttonAddExchangeRate = new JButton("Add ExRate");
		JButton buttonRemoveExchangeRate = new JButton("Remove ExRate");
		JButton buttonExchange= new JButton("Exchange");
		
		panelEast.add(buttonAddExchangeRate);
		panelEast.add(buttonRemoveExchangeRate);
		panelEast.add(buttonExchange);
		
		/*
		 * Adjusting south panel
		 */
		JTextArea textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(WIDTH - 2, HEIGHT / 10));
		
		Border border = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(border, "STATUS");
		
		panelSouth.setBorder(titled);
	
		panelSouth.add(new JScrollPane(textArea));
		
		/*
		 * Adjusting central panel
		 */
		panelCenter.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT/10));
		
		String[] columnNames = {"ID", "Abbreviation", "Selling", "Middle", "Buying", "Name"};
		Object[][] rows = {null, null, null, null, null, null};
		
		JTable table = new JTable(new ExchangeRateTableModel());
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		panelCenter.add(scrollPane);
		
		/*
		 * Adjusting menu bar
		 */
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu separatorMenu = new JMenu("|");
		JMenu helpMenu = new JMenu("Help");
		
		separatorMenu.setEnabled(false);
		
		JMenuItem itemOpen = new JMenuItem("Open");
		JMenuItem itemSave = new JMenuItem("Save");
		JMenuItem itemExit = new JMenuItem("Exit");
		
		JMenuItem itemAbout = new JMenuItem("About");
		
		fileMenu.add(itemOpen);
		fileMenu.add(itemSave);
		fileMenu.add(itemExit);
		
		helpMenu.add(itemAbout);
		
		menuBar.add(fileMenu);
		menuBar.add(separatorMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		/*
		 * Adding panels to frame...
		 */
		add(panelCenter, BorderLayout.CENTER);
		add(panelEast, BorderLayout.EAST);
		add(panelSouth, BorderLayout.SOUTH);
		
		pack();
	}
}
