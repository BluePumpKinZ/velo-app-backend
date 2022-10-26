package be.kdg.sa.velo.domain.users;

import javax.persistence.*;



@Entity(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private int id;
	@Column (name = "Name")
	private String name;
	@Column (name = "Email")
	private String email;
	@Column (name = "Street")
	private String street;
	@Column (name = "StreetNumber")
	private String streetNumber;
	@Column (name = "PostalCode")
	private String postalCode;
	@Column (name = "City")
	private String city;
	@Column (name = "Country")
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
