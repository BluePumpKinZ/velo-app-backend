package be.kdg.sa.velo.domain.subscriptions;

import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.users.User;

import javax.persistence.*;
import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:48
 */
@Entity (name = "Subscriptions")
public class Subscription {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "SubscriptionId")
	private long id;
	@OneToOne (optional = true)
	private User user;
	private SubscriptionType subscriptionType;
	private long startDate;
	private boolean isActive;
	@OneToMany (mappedBy = "subscription")
	private List<Ride> rides;
	
	public Subscription (long id, User user, SubscriptionType subscriptionType, long startDate, boolean isActive, List<Ride> rides) {
		this.rides = rides;
		setId (id);
		setUser (user);
		setSubscriptionType (subscriptionType);
		setStartDate (startDate);
		setActive (isActive);
	}
	
	public Subscription () {
	
	}
	
	public long getId () {
		return id;
	}
	
	public void setId (long id) {
		this.id = id;
	}
	
	public User getUser () {
		return user;
	}
	
	public void setUser (User user) {
		this.user = user;
	}
	
	public SubscriptionType getSubscriptionType () {
		return subscriptionType;
	}
	
	public void setSubscriptionType (SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	public long getStartDate () {
		return startDate;
	}
	
	public void setStartDate (long startDate) {
		this.startDate = startDate;
	}
	
	public boolean isActive () {
		return isActive;
	}
	
	public void setActive (boolean active) {
		isActive = active;
	}
	
}
