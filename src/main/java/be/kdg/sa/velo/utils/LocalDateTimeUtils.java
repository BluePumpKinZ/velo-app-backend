package be.kdg.sa.velo.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
public class LocalDateTimeUtils {
	
	public static long toUTCMillis (LocalDateTime dateTime) {
		return dateTime.toInstant (ZoneOffset.UTC).toEpochMilli ();
	}
	
	public static LocalDateTime fromUTCMillis (long millis) {
		return LocalDateTime.ofEpochSecond (millis / 1000, 0, ZoneOffset.UTC);
	}
	
}
