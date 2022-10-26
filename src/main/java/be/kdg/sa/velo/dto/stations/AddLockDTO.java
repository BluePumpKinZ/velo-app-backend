package be.kdg.sa.velo.dto.stations;

import javax.validation.constraints.Positive;


public class AddLockDTO {
	
	@Positive
	public int stationLockNr;
	@Positive
	public int stationId;
	public Integer vehicleId;
	
}
