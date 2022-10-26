package be.kdg.sa.velo.models.stations;

import be.kdg.sa.velo.domain.stations.Station;


public class StationModel {
	
	public int id;
	public String objectId;
	public String stationNr;
	public String type;
	public String street;
	public String number;
	public String zipCode;
	public String district;
	public double latitude;
	public double longitude;
	public String additionalInfo;
	
	public static StationModel FromStation (Station station) {
		StationModel model = new StationModel();
		model.id = station.getId();
		model.objectId = station.getObjectId();
		model.stationNr = station.getStationNr();
		model.type = station.getType();
		model.street = station.getStreet();
		model.number = station.getNumber();
		model.zipCode = station.getZipCode();
		model.district = station.getDistrict();
		model.latitude = station.getGpsCoord().getX();
		model.longitude = station.getGpsCoord().getY();
		model.additionalInfo = station.getAdditionalInfo();
		return model;
	}
	
}
