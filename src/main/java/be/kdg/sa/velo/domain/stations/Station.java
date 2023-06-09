package be.kdg.sa.velo.domain.stations;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Stations")
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StationId")
	private int id;
	@Column(name = "ObjectId", length=20, nullable = false)
	private String objectId;
	@Column(name = "StationNr", length=20, nullable = false)
	private String stationNr;
	@Column(name = "Type", length=20, nullable = false)
	private String type;
	@Column(name = "Street", length=100, nullable = false)
	private String street;
	@Column(name = "Number", length=10)
	private String number;
	@Column(name = "ZipCode", length=10, nullable = false)
	private String zipCode;
	@Column(name = "District", length=100, nullable = false)
	private String district;
	@Column(name = "GPSCoord")
	private Point gpsCoord;
	@Column(name = "AdditionalInfo", length=100)
	private String additionalInfo;
	@OneToMany(mappedBy = "station")
	private List<Lock> locks;
	
	public Station () {
	
	}
	
	public Station (String objectId, String stationNr, String type, String street, String number, String zipCode, String district, Point gpsCoord, String additionalInfo) {
		this.objectId = objectId;
		this.stationNr = stationNr;
		this.type = type;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.district = district;
		this.gpsCoord = gpsCoord;
		this.additionalInfo = additionalInfo;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getObjectId () {
		return objectId;
	}
	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	public String getStationNr () {
		return stationNr;
	}
	
	public void setStationNr (String stationNr) {
		this.stationNr = stationNr;
	}
	
	public String getType () {
		return type;
	}
	
	public void setType (String type) {
		this.type = type;
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
	
	public String getZipCode () {
		return zipCode;
	}
	
	public void setZipCode (String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getDistrict () {
		return district;
	}
	
	public void setDistrict (String district) {
		this.district = district;
	}
	
	public Point getGpsCoord () {
		return gpsCoord;
	}
	
	public void setGpsCoord (Point gpsCoord) {
		this.gpsCoord = gpsCoord;
	}
	
	public String getAdditionalInfo () {
		return additionalInfo;
	}
	
	public void setAdditionalInfo (String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	public List<Lock> getLocks () {
		return locks;
	}
	
	public void setLocks (List<Lock> locks) {
		this.locks = locks;
	}
}
