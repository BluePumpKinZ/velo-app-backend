package be.kdg.sa.velo.dto.stations;

import javax.validation.constraints.Positive;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
public class AddLockDTO {
	
	@Positive
	public int stationLockNr;
	@Positive
	public int stationId;
	public Integer vehicleId;
	
}
