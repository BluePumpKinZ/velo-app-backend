package be.kdg.sa.velo.models.stations;

import be.kdg.sa.velo.domain.stations.Lock;

import java.util.List;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
public class AvailableLocksModel {
	
	public List<AvailableLock> locks;
	
	public static class AvailableLock {
		
		public int lockId;
		public int lockNr;
		
		private AvailableLock (int lockId, int lockNr) {
			this.lockId = lockId;
			this.lockNr = lockNr;
		}
	}
	
	public static AvailableLocksModel FromLocks (List<Lock> locks) {
		var result = new AvailableLocksModel ();
		result.locks = locks.stream ().map (lock -> new AvailableLocksModel.AvailableLock (lock.getId (), lock.getStationLockNr ())).toList ();
		return result;
	}
	
}


