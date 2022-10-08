package be.kdg.sa.velo.services.prices.priceitems.undocked;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionTypeEnum;
import be.kdg.sa.velo.services.prices.priceitems.PriceItem;
import be.kdg.sa.velo.utils.SubscriptionUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
@Component
public final class UndockedRideUnlockFeePriceItem extends PriceItem {
	
	private static final Map<SubscriptionTypeEnum, Double> prices = new HashMap<> () {{
		put (SubscriptionTypeEnum.DAY, 1.5);
		put (SubscriptionTypeEnum.MONTH, 1.0);
		put (SubscriptionTypeEnum.YEAR, 1.0);
	}};
	
	private UndockedRideUnlockFeePriceItem () {
		super ("Unlock fee");
	}
	
	@Override
	protected double getPrice (Ride ride) {
		return prices.get (SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription ()));
	}
}

