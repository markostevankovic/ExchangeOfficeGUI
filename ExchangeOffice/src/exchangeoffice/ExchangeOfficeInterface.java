package exchangeoffice;

import java.util.ArrayList;

public interface ExchangeOfficeInterface
{
	public void addNewCurrency(Currency currency);
	public void removeCurrency(Currency currency);
	public double executeTransaction(Currency currency, boolean forSelling, double amount);
	public ArrayList<Currency> getAllCurrencies();
}
