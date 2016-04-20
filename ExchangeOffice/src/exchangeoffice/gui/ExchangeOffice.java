package exchangeoffice.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;

import model.ExchangeRateTableModel;

/*
 * All code is generated MANUALY.
 * 
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
		
		itemOpen.setIcon(new ImageIcon(ExchangeOffice.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		itemSave.setIcon(new ImageIcon(ExchangeOffice.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, InputEvent.CTRL_MASK));
		itemSave.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, InputEvent.CTRL_MASK));
		itemExit.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_X, InputEvent.ALT_MASK));
		
		JMenuItem itemAbout = new JMenuItem("About");
		
		/*
		fileMenu.add(itemOpen);
		fileMenu.add(itemSave);
		fileMenu.add(itemExit);
		*/
		helpMenu.add(itemAbout);
		
		menuBar.add(fileMenu);
		menuBar.add(separatorMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		/*
		 * Adjusting central panel
		 */
		panelCenter.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT / 10));
		
		JTable table = new JTable(new ExchangeRateTableModel());
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(WIDTH - 150, HEIGHT - HEIGHT / 10 - 10));
		
		JPopupMenu popUpMenu = new JPopupMenu();
		
		popUpMenu.add(itemOpen);
		popUpMenu.add(itemSave);
		popUpMenu.add(itemExit);
		
		table.setComponentPopupMenu(popUpMenu);
		
		panelCenter.add(scrollPane);
		
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
		 * Adding panels to frame...
		 */
		add(panelCenter, BorderLayout.CENTER);
		add(panelEast, BorderLayout.EAST);
		add(panelSouth, BorderLayout.SOUTH);
		
		pack();
	}
}
