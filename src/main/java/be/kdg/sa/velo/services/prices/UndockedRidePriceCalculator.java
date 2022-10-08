package be.kdg.sa.velo.services.prices;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.models.invoices.InvoiceLineItem;
import be.kdg.sa.velo.services.prices.priceitems.undocked.UndockedRidePricePerKmPriceItem;
import be.kdg.sa.velo.services.prices.priceitems.undocked.UndockedRidePricePerMinPriceItem;
import be.kdg.sa.velo.services.prices.priceitems.undocked.UndockedRideUnlockFeePriceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
@SuppressWarnings ("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class UndockedRidePriceCalculator implements RidePriceCalculator {
	
	@Autowired
	private UndockedRideUnlockFeePriceItem undockedRideUnlockFeePriceItem;
	@Autowired
	private UndockedRidePricePerMinPriceItem undockedRidePricePerMinPriceItem;
	@Autowired
	private UndockedRidePricePerKmPriceItem undockedRidePricePerKmPriceItem;
	
	@Override
	public List<InvoiceLineItem> getInvoiceLineItems (Ride ride) {
		return List.of(undockedRideUnlockFeePriceItem.getInvoiceLineItem(ride),
				undockedRidePricePerMinPriceItem.getInvoiceLineItem(ride),
				undockedRidePricePerKmPriceItem.getInvoiceLineItem(ride));
	}
}

