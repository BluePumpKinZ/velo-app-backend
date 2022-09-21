package be.kdg.sa.velo.domain;

import be.kdg.sa.velo.domain.vehicles.Vehicle;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:41
 */
public class Lock {
	private long id;
	private Station station;
	private Vehicle vehicle;
	private boolean isAvailable;
	private boolean isBroken;
	private int stationLockNr;
}
