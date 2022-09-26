package be.kdg.sa.velo.domain.stations;

import be.kdg.sa.velo.domain.vehicles.DockedVehicle;

import javax.persistence.*;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:41
 */
@Entity(name = "Locks")
public class Lock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LockId")
	private long id;
	@OneToOne (optional = true)
	private DockedVehicle vehicle;
	private int stationLockNr;
	@ManyToOne()
	private Station station;
	
	public Lock () {
	}
	
	public Lock (long id, int stationLockNr, Station station) {
		setId(id);
		setStationLockNr (stationLockNr);
		setStation (station);
	}
	
	public void setVehicle (DockedVehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setStationLockNr (int stationLockNr) {
		this.stationLockNr = stationLockNr;
	}
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public DockedVehicle getVehicle () {
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
