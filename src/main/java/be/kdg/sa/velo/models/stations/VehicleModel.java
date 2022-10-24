package be.kdg.sa.velo.models.stations;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
public class VehicleModel {
	
	public int id;
	public String serialNumber;
	public int vehicleLotId;
	public long lastMaintenanceOn;
	public int lockId;
	public double latitude;
	public double longitude;
	
	public static VehicleModel FromVehicle (Vehicle vehicle) {
		var model = new VehicleModel ();
		model.id = vehicle.getId ();
		model.serialNumber = vehicle.getSerialNumber ();
		model.vehicleLotId = vehicle.getLot ().getId ();
		model.lastMaintenanceOn = LocalDateTimeUtils.toUTCMillis (vehicle.getLastMaintenanceDate ());
		model.latitude = vehicle.getLocation ().getX ();
		model.longitude = vehicle.getLocation ().getY ();
		return model;
	}
	
}
