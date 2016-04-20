package exchangeoffice;

import java.util.ArrayList;

public interface ExchangeOfficeInterface
{
	public void addNewExchangeRate(ExchangeRate rate);
	public void obrisiValutu(ExchangeRate rate);
	public double izvrsiTransakciju(ExchangeRate rate, boolean forSelling, double amount);
	public ArrayList<ExchangeRate> getExchangeRates();
}
