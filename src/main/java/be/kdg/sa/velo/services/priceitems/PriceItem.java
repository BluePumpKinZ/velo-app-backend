package be.kdg.sa.velo.services.priceitems;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.invoices.InvoiceLineItem;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public abstract class PriceItem {
	
	private final String description;
	
	protected abstract double getPrice (Ride ride);
	
	public abstract boolean doesApply (Ride ride);
	
	protected PriceItem (String description) {
		this.description = description;
	}
	
	public InvoiceLineItem getInvoiceLineItem (Ride ride) {
		return new InvoiceLineItem(description, getPrice (ride));
	}
}
