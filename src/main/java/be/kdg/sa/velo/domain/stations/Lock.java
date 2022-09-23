package be.kdg.sa.velo.domain.stations;

import be.kdg.sa.velo.domain.subscriptions.vehicles.DockedVehicle;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:41
 */
public class Lock {
	private final long id;
	private DockedVehicle vehicle;
	private boolean isAvailable;
	private boolean isBroken;
	private int stationLockNr;
	
	public Lock (long id, boolean isAvailable, boolean isBroken, int stationLockNr) {
		this.id = id;
		setAvailable (isAvailable);
		setBroken (isBroken);
		setStationLockNr (stationLockNr);
	}
	
	public void setVehicle (DockedVehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setAvailable (boolean available) {
		isAvailable = available;
	}
	
	public void setBroken (boolean broken) {
		isBroken = broken;
	}
	
	public void setStationLockNr (int stationLockNr) {
		this.stationLockNr = stationLockNr;
	}
	
	public long getId () {
		return id;
	}
	
	public DockedVehicle getVehicle () {
		return vehicle;
	}
	
	public boolean isAvailable () {
		return isAvailable;
	}
	
	public boolean isBroken () {
		return isBroken;
	}
	
	public int getStationLockNr () {
		return stationLockNr;
	}
}
