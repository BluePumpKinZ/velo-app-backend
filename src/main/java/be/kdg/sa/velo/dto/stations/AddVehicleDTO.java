package be.kdg.sa.velo.dto.stations;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.utils.LocalDateTimeUtils;
import be.kdg.sa.velo.utils.PointUtils;

/**
 * Jonas Leijzen
 * 23/10/2022
 */
public class AddVehicleDTO {
	
	public String serialNumber;
	public int vehicleLotId;
	public long lastMaintenanceOn;
	public double latitude;
	public double longitude;
	
	public Vehicle toVehicle () {
		var vehicle = new Vehicle ();
		vehicle.setSerialNumber (serialNumber);
		vehicle.setLastMaintenanceDate (LocalDateTimeUtils.fromUTCMillis (lastMaintenanceOn));
		vehicle.setLocation (PointUtils.createPoint (latitude, longitude));
		return vehicle;
	}
	
}
