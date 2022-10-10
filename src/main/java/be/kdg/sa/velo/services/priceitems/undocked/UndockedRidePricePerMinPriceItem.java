package be.kdg.sa.velo.services.priceitems.undocked;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.rides.RideType;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionTypeEnum;
import be.kdg.sa.velo.services.priceitems.PriceItem;
import be.kdg.sa.velo.services.ride.RideTimeCalculator;
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
public final class UndockedRidePricePerMinPriceItem extends PriceItem {
	
	private static final Map<SubscriptionTypeEnum, Double> prices = new HashMap<> () {{
		put (SubscriptionTypeEnum.DAY, 0.20);
		put (SubscriptionTypeEnum.MONTH, 0.12);
		put (SubscriptionTypeEnum.YEAR, 0.08);
	}};
	
	private final RideTimeCalculator rideTimeCalculator;
	
	private UndockedRidePricePerMinPriceItem (RideTimeCalculator rideTimeCalculator) {
		super ("Duration");
		this.rideTimeCalculator = rideTimeCalculator;
	}
	
	@Override
	public boolean doesApply (Ride ride) {
		return RideUtils.getRideType (ride) == RideType.UNDOCKED;
	}
	
	@Override
	protected double getPrice (Ride ride) {
		var minutes = rideTimeCalculator.getRideDuration (ride).toMinutes ();
		var subscriptionType = SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription ());
		return prices.get(subscriptionType) * minutes;
	}
}
