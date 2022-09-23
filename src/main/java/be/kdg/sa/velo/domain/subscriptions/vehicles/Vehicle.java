package be.kdg.sa.velo.domain.subscriptions.vehicles;

import java.time.LocalDate;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:38
 */
public abstract class Vehicle {
	
	private final long id;
	private String serialNumber;
	private int cityId;
	private VehicleType type;
	private String image;
	private boolean isAvailable;
	private LocalDate lastServiceDate;
	
	public Vehicle (long id, String serialNumber, int cityId, VehicleType type, String image, boolean isAvailable, LocalDate lastServiceDate) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.cityId = cityId;
		this.type = type;
		this.image = image;
		this.isAvailable = isAvailable;
		this.lastServiceDate = lastServiceDate;
	}
	
	public long getId () {
		return id;
	}
	
	public String getSerialNumber () {
		return serialNumber;
	}
	
	public void setSerialNumber (String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public int getCityId () {
		return cityId;
	}
	
	public void setCityId (int cityId) {
		this.cityId = cityId;
	}
	
	public VehicleType getType () {
		return type;
	}
	
	public void setType (VehicleType type) {
		this.type = type;
	}
	
	public String getImage () {
		return image;
	}
	
	public void setImage (String image) {
		this.image = image;
	}
	
	public boolean isAvailable () {
		return isAvailable;
	}
	
	public void setAvailable (boolean available) {
		isAvailable = available;
	}
	
	public LocalDate getLastServiceDate () {
		return lastServiceDate;
	}
	
	public void setLastServiceDate (LocalDate lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
}
