package be.kdg.sa.velo.services.prices;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.invoices.InvoiceLineItem;

import java.util.List;

/**
 * Jonas Leijzen
 * 7/10/2022
 */
public interface RidePriceCalculator {
	List<InvoiceLineItem> getInvoiceLineItems (Ride ride);
}
