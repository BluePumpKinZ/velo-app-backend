package be.kdg.sa.velo.services.priceitems.undocked;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.rides.RideType;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionTypeEnum;
import be.kdg.sa.velo.services.ride.RideDistanceCalculator;
import be.kdg.sa.velo.services.priceitems.PriceItem;
import be.kdg.sa.velo.utils.RideUtils;
import be.kdg.sa.velo.utils.SubscriptionUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public final class UndockedRidePricePerKmPriceItem extends PriceItem {
	
	private static final Map<SubscriptionTypeEnum, Double> prices = new HashMap<> () {{
		put (SubscriptionTypeEnum.DAY, 0.30);
		put (SubscriptionTypeEnum.MONTH, 0.22);
		put (SubscriptionTypeEnum.YEAR, 0.12);
	}};
	
	private final RideDistanceCalculator rideDistanceCalculator;
	
	private UndockedRidePricePerKmPriceItem (RideDistanceCalculator rideDistanceCalculator) {
		super ("Distance");
		this.rideDistanceCalculator = rideDistanceCalculator;
	}
	
	@Override
	public boolean doesApply (Ride ride) {
		return RideUtils.getRideType (ride) == RideType.UNDOCKED;
	}
	
	@Override
	protected double getPrice (Ride ride) {
		var subscriptionType = SubscriptionUtils.getSubscriptionTypeEnum (ride.getSubscription ());
		var distance = rideDistanceCalculator.getRideDistance (ride);
		return prices.get (subscriptionType) * distance;
	}
}
