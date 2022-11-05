package be.kdg.sa.velo.domain.stations;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import lombok.*;

import javax.persistence.*;

@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter

@Entity(name = "Locks")
public class Lock {
	@Id
	@Column(name = "LockId")
	private int id;
	@OneToOne (optional = true)
	@JoinColumn(name = "VehicleId")
	private Vehicle vehicle;
	@Column(name = "StationLockNr")
	private int stationLockNr;
	@ManyToOne
	@JoinColumn(name = "StationId")
	private Station station;
	
	public Lock () {
	}
	
	public Lock (int lockId, int stationLockNr) {
		this.id = lockId;
		this.stationLockNr = stationLockNr;
	}
	
	public void setVehicle (Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setStationLockNr (int stationLockNr) {
		this.stationLockNr = stationLockNr;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public Vehicle getVehicle () {
		return vehicle;
	}
	
	public int getStationLockNr () {
		return stationLockNr;
	}
	
	public Station getStation () {
		return station;
	}
	
	public void setStation (Station station) {
		this.station = station;
	}
	
	public boolean isAvailable () {
		return vehicle == null;
	}
}
