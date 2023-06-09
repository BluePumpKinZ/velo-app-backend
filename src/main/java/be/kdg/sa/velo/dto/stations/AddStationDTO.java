package be.kdg.sa.velo.dto.stations;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


public class AddStationDTO {
	
	@NotEmpty
	@Length (max = 20)
	public String objectId;
	@NotEmpty
	@Length (max = 20)
	public String stationNr;
	@NotEmpty
	@Length (max = 20)
	public String type;
	@NotEmpty
	@Length (max = 100)
	public String street;
	@NotEmpty
	@Length (max = 10)
	public String number;
	@NotEmpty
	@Length (max = 10)
	public String zipCode;
	@NotEmpty
	@Length (max = 100)
	public String district;
	@NotEmpty
	@Range (min = -180, max = 180)
	public double latitude;
	@NotEmpty
	@Range (min = -180, max = 180)
	public double longitude;
	public String additionalInfo;
	@Positive
	public int labelId;
	@Positive
	public int cityId;

	public AddStationDTO(String objectId, String stationNr, String type, String street, String number, String zipCode, String district, double latitude, double longitude) {
		this.objectId = objectId;
		this.stationNr = stationNr;
		this.type = type;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.district = district;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public AddStationDTO() {
	}
}
