package be.kdg.sa.velo.domain.users;

import javax.persistence.*;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:48
 */

@Entity(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private int id;
	private String name;
	private String email;
	private String street;
	private String streetNumber;
	private String postalCode;
	private String city;
	private String country;
	
	public User () {
	
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getStreet () {
		return street;
	}
	
	public void setStreet (String street) {
		this.street = street;
	}
	
	public String getStreetNumber () {
		return streetNumber;
	}
	
	public void setStreetNumber (String streetNumber) {
		this.streetNumber = streetNumber;
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
	
	public String getCountry () {
		return country;
	}
	
	public void setCountry (String country) {
		this.country = country;
	}
	
}
