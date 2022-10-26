package be.kdg.sa.velo.services.priceitems.docked;

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


@Component
public final class DockedRidePricePerMinPriceItem extends PriceItem {
	
	private static final Map<SubscriptionTypeEnum, Double> prices = new HashMap<> () {{
		put (SubscriptionTypeEnum.DAY, 0.15);
		put (SubscriptionTypeEnum.MONTH, 0.10);
		put (SubscriptionTypeEnum.YEAR, 0.08);
	}};
	
	private final RideTimeCalculator rideTimeCalculator;
	
	private DockedRidePricePerMinPriceItem (RideTimeCalculator rideTimeCalculator) {
		super ("Time");
		this.rideTimeCalculator = rideTimeCalculator;
	}
	
	@Override
	public boolean doesApply (Ride ride) {
		return RideUtils.getRideType (ride) == RideType.DOCKED;
	}
	
	@Override
	protected double getPrice (Ride ride) {
		var subscriptionType = SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription ());
		var duration = rideTimeCalculator.getRideDuration (ride);
		return prices.get (subscriptionType) * duration.getSeconds () / 60;
	}
}
