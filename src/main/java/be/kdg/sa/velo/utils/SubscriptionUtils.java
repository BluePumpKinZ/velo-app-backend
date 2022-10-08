package be.kdg.sa.velo.utils;

import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionType;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionTypeEnum;

/**
 * Jonas Leijzen
 * 8/10/2022
 */
public class SubscriptionUtils {
	
	public static SubscriptionTypeEnum getTypeEnum (SubscriptionType subscriptionType) {
		return switch (subscriptionType.getDescription ()) {
			case "DAG" -> SubscriptionTypeEnum.DAY;
			case "MAAND" -> SubscriptionTypeEnum.MONTH;
			case "JAAR" -> SubscriptionTypeEnum.YEAR;
			default -> throw new RuntimeException (String.format ("SubscriptionType '%s' not found", subscriptionType.getDescription ()));
		};
	}
	
	public static SubscriptionTypeEnum getSubscriptionTypeEnum (Subscription subscription) {
		return getTypeEnum (subscription.getSubscriptionType ());
	}
	
}
