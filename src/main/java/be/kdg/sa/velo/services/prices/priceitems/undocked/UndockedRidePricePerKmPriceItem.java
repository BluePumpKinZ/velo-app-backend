package be.kdg.sa.velo.services.prices.priceitems.undocked;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionTypeEnum;
import be.kdg.sa.velo.services.RideService;
import be.kdg.sa.velo.services.prices.priceitems.PriceItem;
import be.kdg.sa.velo.utils.SubscriptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
@Component
public final class UndockedRidePricePerKmPriceItem extends PriceItem {
	
	@Autowired
	private RideService rideService;
	
	private static final Map<SubscriptionTypeEnum, Double> prices = new HashMap<> () {{
		put (SubscriptionTypeEnum.DAY, 0.30);
		put (SubscriptionTypeEnum.MONTH, 0.22);
		put (SubscriptionTypeEnum.YEAR, 0.12);
	}};
	
	private UndockedRidePricePerKmPriceItem () {
		super ("Distance");
	}
	
	@Override
	protected double getPrice (Ride ride) {
		var subscriptionType = SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription ());
		var distance = rideService.getRideDistance (ride);
		return prices.get (subscriptionType) * distance;
	}
}
