package be.kdg.sa.velo.models.invoices;

import java.util.ArrayList;
import java.util.List;

/**
 * Jonas Leijzen
 * 5/10/2022
 */
public class Invoice {
	
	private final List<InvoiceLineItem> lineItems = new ArrayList<> ();
	
	public void addLineItem (InvoiceLineItem lineItem) {
		lineItems.add (lineItem);
	}
	
	public List<InvoiceLineItem> getLineItems () {
		return lineItems;
	}
	
	public double getTotalPrice () {
		return lineItems.stream ().mapToDouble (InvoiceLineItem::getPrice).sum ();
	}
}
