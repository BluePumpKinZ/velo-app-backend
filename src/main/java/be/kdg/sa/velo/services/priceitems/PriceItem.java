package be.kdg.sa.velo.services.priceitems;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.invoices.InvoiceLineItem;
import be.kdg.sa.velo.services.RideDistanceCalculator;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public abstract class PriceItem {
	
	private final String description;
	
	protected abstract double getPrice (Ride ride, RideDistanceCalculator rideDistanceCalculator);
	
	public abstract boolean doesApply (Ride ride);
	
	protected PriceItem (String description) {
		this.description = description;
	}
	
	public InvoiceLineItem getInvoiceLineItem (Ride ride, RideDistanceCalculator rideDistanceCalculator) {
		return new InvoiceLineItem(description, getPrice (ride, rideDistanceCalculator));
	}
}
