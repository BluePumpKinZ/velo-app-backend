package be.kdg.sa.velo.domain.stations;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:42
 */
public class Station {
	private final long id;
	private String name;
	private String street;
	private String number;
	private String postalCode;
	private String city;
	private final Set<Lock> locks = new HashSet<> ();
	private double latitude;
	private double longitude;
	
	public Station (long id, String name, String street, String number, String postalCode, String city, int capacity, double latitude, double longitude) {
		this.id = id;
		setName (name);
		setStreet (street);
		setNumber (number);
		setPostalCode (postalCode);
		setCity (city);
		setLatitude (latitude);
		setLongitude (longitude);
	}
	
	public int getCapacity () {
		return locks.size ();
	}
	
	public long getId () {
		return id;
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
	
	public Set<Lock> getLocks () {
		return locks;
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
