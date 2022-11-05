package be.kdg.sa.velo.dto.stations;

import lombok.*;

import javax.validation.constraints.Positive;

//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class AddLockDTO {
	@Positive
	public int lockId;
	@Positive
	public int stationLockNr;
	@Positive
	public int stationId;
	public Integer vehicleId;

	public AddLockDTO(int lockId, int stationLockNr, int stationId, Integer vehicleId) {
		this.lockId = lockId;
		this.stationLockNr = stationLockNr;
		this.stationId = stationId;
		this.vehicleId = vehicleId;
	}

	public AddLockDTO() {
	}

	public int getStationLockNr() {
		return stationLockNr;
	}

	public void setStationLockNr(int stationLockNr) {
		this.stationLockNr = stationLockNr;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
}
