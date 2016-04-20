package exchangeoffice.gui;

import javax.swing.JFrame;

public class MainClass 
{
	public static void main(String[] args) 
	{
		ExchangeOffice exchangeOffice = new ExchangeOffice();
		exchangeOffice.setVisible(true);
		exchangeOffice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
