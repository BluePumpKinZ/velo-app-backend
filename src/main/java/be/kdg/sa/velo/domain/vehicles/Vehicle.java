package be.kdg.sa.velo.domain.vehicles;

import be.kdg.sa.velo.domain.Lock;

import java.util.Date;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:38
 */
public class Vehicle {
	private long id;
	private String SerialNumber;
	private int CityId;
	private VehicleType type;
	private String image;
	private Lock lock;
	private boolean isAvailable;
	private Date LastServiceDate;
}
