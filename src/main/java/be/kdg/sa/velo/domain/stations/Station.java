package be.kdg.sa.velo.domain.stations;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:42
 */
@Entity(name = "Stations")
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StationId")
	private long id;
	private String name;
	private String street;
	private String number;
	private String postalCode;
	private String city;
	@OneToMany(mappedBy = "station")
	private List<Lock> locks;
	private double latitude;
	private double longitude;
	
	public Station (long id, String name, String street, String number, String postalCode, String city, int capacity, double latitude, double longitude) {
		setId(id);
		setName (name);
		setStreet (street);
		setNumber (number);
		setPostalCode (postalCode);
		setCity (city);
		setLatitude (latitude);
		setLongitude (longitude);
	}
	
	public Station () {
	
	}
	
	public int getCapacity () {
		return locks.size ();
	}
	
	public long getId () {
		return id;
	}
	
	private void setId (long id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getStreet () {
		return street;
	}
	
	public void setStreet (String street) {
		this.street = street;
	}
	
	public String getNumber () {
		return number;
	}
	
	public void setNumber (String number) {
		this.number = number;
	}
	
	public String getPostalCode () {
		return postalCode;
	}
	
	public void setPostalCode (String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCity () {
		return city;
	}
	
	public void setCity (String city) {
		this.city = city;
	}
	
	public List<Lock> getLocks () {
		return locks;
	}
	
	public void setLocks (List<Lock> locks) {
		this.locks = locks;
	}
	
	public int getAvailableBikes () {
		return (int)locks.stream ().filter((lock -> lock.getVehicle () != null)).count ();
	}
	
	public int getAvailableLocks () {
		return (int)locks.stream ().filter (Lock::isAvailable).count ();
	}
	
	public double getLatitude () {
		return latitude;
	}
	
	public void setLatitude (double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude () {
		return longitude;
	}
	
	public void setLongitude (double longitude) {
		this.longitude = longitude;
	}
}
