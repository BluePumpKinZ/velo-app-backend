package be.kdg.sa.velo.services.prices.priceitems.docked;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionTypeEnum;
import be.kdg.sa.velo.services.prices.priceitems.PriceItem;
import be.kdg.sa.velo.utils.RideUtils;
import be.kdg.sa.velo.utils.SubscriptionUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
@Component
public final class DockedRidePricePerMinPriceItem extends PriceItem {
	
	private static final Map<SubscriptionTypeEnum, Double> prices = new HashMap<> () {{
		put (SubscriptionTypeEnum.DAY, 0.20);
		put (SubscriptionTypeEnum.MONTH, 0.12);
		put (SubscriptionTypeEnum.YEAR, 0.08);
	}};
	
	private DockedRidePricePerMinPriceItem () {
		super ("Time");
	}
	
	@Override
	protected double getPrice (Ride ride) {
		var subscriptionType = SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription ());
		var duration = RideUtils.getRideDuration (ride);
		return prices.get (subscriptionType) * duration.getSeconds () / 60;
	}
}
