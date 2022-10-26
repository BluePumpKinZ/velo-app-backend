package be.kdg.sa.velo.models.stations;

import be.kdg.sa.velo.domain.stations.Lock;


public class LockModel {
	
	public int lockId;
	public int stationLockNr;
	public int stationId;
	public Integer vehicleId;
	
	public static LockModel FromLock (Lock lock) {
		var model = new LockModel ();
		model.lockId = lock.getId ();
		model.stationLockNr = lock.getStationLockNr ();
		model.stationId = lock.getStation ().getId ();
		model.vehicleId = lock.getVehicle () != null ? lock.getVehicle ().getId () : null;
		return model;
	}
	
}
