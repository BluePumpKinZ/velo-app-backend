package be.kdg.sa.velo.models.invoices;


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
