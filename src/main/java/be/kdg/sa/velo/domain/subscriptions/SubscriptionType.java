package be.kdg.sa.velo.domain.subscriptions;

import javax.persistence.*;


@Entity(name = "SubscriptionTypes")
public class SubscriptionType {
	@Id
	@Column (name = "SubscriptionTypeId", columnDefinition = "TINYINT")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	@Column (name = "Description", columnDefinition = "VARCHAR(50)", nullable = false)
	private String description;
	
	public SubscriptionType () {
	}
	
	public SubscriptionType (String description) {
		this.description = description;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
}
