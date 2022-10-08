package be.kdg.sa.velo.services.prices.priceitems;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.invoices.InvoiceLineItem;
import be.kdg.sa.velo.utils.SubscriptionUtils;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public abstract class PriceItem {
	
	private final String description;
	
	protected abstract double getPrice (Ride ride);
	
	protected PriceItem (String description) {
		this.description = description;
	}
	
	public InvoiceLineItem getInvoiceLineItem (Ride ride) {
		var subscriptionType = SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription());
		return new InvoiceLineItem(description, getPrice (ride));
	}
}
