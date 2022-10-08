package be.kdg.sa.velo.services.prices;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.invoices.InvoiceLineItem;
import be.kdg.sa.velo.services.prices.priceitems.docked.DockedRidePricePerMinPriceItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
@SuppressWarnings ("SpringJavaAutowiredMembersInspection")
public final class DockedRidePriceCalculator implements RidePriceCalculator {
	
	@Autowired
	private DockedRidePricePerMinPriceItem dockedRidePricePerMinPriceItem;
	
	@Override
	public List<InvoiceLineItem> getInvoiceLineItems (Ride ride) {
		return List.of (
			dockedRidePricePerMinPriceItem.getInvoiceLineItem (ride)
		);
	}
}
