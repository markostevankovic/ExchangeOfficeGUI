package exchangeoffice;

import java.util.ArrayList;

public class ExchangeOfice implements ExchangeOfficeInterface
{
	private ArrayList<Currency> currencies;
	private static ExchangeOfice object;
	
	/*
	 * Singleton pattern...
	 */
	public static ExchangeOfice getInstanceOfExchangeOffice()
	{
		if(object == null)
			object = new ExchangeOfice();
		return object;
	}
	
	private ExchangeOfice()
	{
		currencies = new ArrayList<Currency>();
	}
	
	@Override
	public ArrayList<Currency> getAllCurrencies()
	{
		return currencies;
	}

	@Override
	public void addNewCurrency(Currency currency) 
	{
		if(currency == null)
			throw new RuntimeException();
		
		if(currencies.contains(currency))
			throw new RuntimeException();
		
		currencies.add(currency);
	}

	@Override
	public void removeCurrency(Currency currency) 
	{
		if(!(currencies.contains(currency)))
			throw new RuntimeException();
		
		currencies.remove(currency);
	}

	@Override
	public double executeTransaction(Currency currency, boolean forSelling, double amount)
	{
		if (forSelling)
			return amount * currency.getSelling();
		else
			return amount * currency.getBuying();
	}
	
}
