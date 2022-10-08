package be.kdg.sa.velo.models.invoices;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public final class InvoiceLineItem {
	
	private final String description;
	private final double price;
	
	public InvoiceLineItem (String description, double price) {
		this.description = description;
		this.price = price;
	}
	
	public String getDescription () {
		return description;
	}
	
	public double getPrice () {
		return price;
	}
}
