package exchangeoffice.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
 * @author Marko Stevankovic
 */

public class ExchangeOffice extends JFrame
{
	private JPanel panelCenter;
	private JPanel panelEast;
	private JPanel panelSouth;
	
	public ExchangeOffice()
	{
		setTitle("Exchange office");
		setLayout(new BorderLayout());
		
		panelCenter = new JPanel();
		panelEast = new JPanel();
		panelSouth = new JPanel();
		
		/*
		 * Adding components to east panel
		 */
		JButton buttonAddExchangeRate = new JButton("Add Exchange Rate");
		JButton buttonRemoveExchangeRate = new JButton("Remove Exchange Rate");
		JButton buttonExchange= new JButton("Exchange");
		
		/*
		 * Adding components to south panel
		 */
		JTextArea textArea = new JTextArea();
		
		Border border = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(border, "STATUS");
		
		panelSouth.add(textArea);
		
		/*
		 * Adjusting menu bar
		 */
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem itemOpen = new JMenuItem("Open");
		JMenuItem itemSave = new JMenuItem("Save");
		JMenuItem itemExit = new JMenuItem("Exit");
		
		JMenuItem itemAbout = new JMenuItem("About");
		
		fileMenu.add(itemOpen);
		fileMenu.add(itemSave);
		fileMenu.add(itemExit);
		
		helpMenu.add(itemAbout);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		/*
		 * Adding panels to frame...
		 */
		add(panelCenter, BorderLayout.CENTER);
		add(panelEast, BorderLayout.EAST);
		add(panelSouth, BorderLayout.SOUTH);
	}
}
