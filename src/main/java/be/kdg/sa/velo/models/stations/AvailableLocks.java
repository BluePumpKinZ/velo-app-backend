package be.kdg.sa.velo.models.stations;

import java.util.List;

/**
 * Jonas Leijzen
 * 28/09/2022
 */
public class AvailableLocks {
	
	public List<AvailableLock> locks;
	
	public static class AvailableLock {
		
		public int lockId;
		public int lockNr;
		
		public AvailableLock (int lockId, int lockNr) {
			this.lockId = lockId;
			this.lockNr = lockNr;
		}
	}
	
}


