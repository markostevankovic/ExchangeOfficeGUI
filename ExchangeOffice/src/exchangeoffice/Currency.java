package exchangeoffice;

import java.io.Serializable;

public class Currency implements Serializable
{
	private int id;
	private String symbol;
	private String name;
	private double buying;
	private double selling;
	private double middle;
	
	public Currency(int id, String symbol, String name, double buying, double selling) 
	{
		this.setId(id);
		this.setSymbol(symbol);
		this.setName(name);
		this.setBuying(buying);
		this.setSelling(selling);
		this.setMiddle();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getSymbol() 
	{
		return symbol;
	}

	public void setSymbol(String symbol) 
	{
		this.symbol = symbol;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getBuying() 
	{
		return buying;
	}

	public void setBuying(double buying) 
	{
		this.buying = buying;
	}

	public double getSelling()
	{
		return selling;
	}

	public void setSelling(double selling)
	{
		this.selling = selling;
	}
	
	public void setMiddle()
	{
		this.middle = (this.getSelling() + this.getBuying()) / 2;
	}
	
	public double getMiddle()
	{
		return (getSelling() + getBuying()) / 2;
	}

	@Override
	public String toString()
	{
		return "ExchangeRate\n\tid: " + this.getId()
					+ ",\n\tsymbol: " + this.getSymbol()
					+ ",\n\tname: " + this.getName() 
					+ ",\n\tbuying: " + this.getBuying()
					+ ", \n\tmiddle: " + this.getMiddle()
					+ ",\n\tselling: " + this.getSelling();
	}
}
