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
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String street;
	private String streetNumber;
	private String postalCode;
	private String city;
	private String country;
	private String phoneNumber;
	private boolean isBlocked;
	private boolean isDeleted;
	
	public User (long id, String firstName, String lastName, String email, String street, String streetNumber, String postalCode, String city, String country, String phoneNumber, boolean isBlocked) {
		setId(id);
		setFirstName (firstName);
		setLastName (lastName);
		setEmail (email);
		setStreet (street);
		setStreetNumber (streetNumber);
		setPostalCode (postalCode);
		setCity (city);
		setCountry (country);
		setPhoneNumber (phoneNumber);
		setBlocked (isBlocked);
		isDeleted = false;
	}
	
	public User () {
	
	}
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName () {
		return lastName;
	}
	
	public void setLastName (String lastName) {
		this.lastName = lastName;
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
	
	public String getPhoneNumber () {
		return phoneNumber;
	}
	
	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean isBlocked () {
		return isBlocked;
	}
	
	public void setBlocked (boolean blocked) {
		isBlocked = blocked;
	}
	
	public boolean isDeleted () {
		return isDeleted;
	}
	
	public void setDeleted (boolean deleted) {
		isDeleted = deleted;
	}
}
