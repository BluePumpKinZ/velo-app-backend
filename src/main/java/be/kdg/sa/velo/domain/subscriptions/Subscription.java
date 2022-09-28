package be.kdg.sa.velo.domain.subscriptions;

import be.kdg.sa.velo.domain.users.User;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:48
 */
@Entity (name = "Subscriptions")
public class Subscription {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "SubscriptionId")
	private int id;
	@ManyToOne (optional = false)
	@JoinColumn (name = "UserId", foreignKey = @ForeignKey (name = "UserId"))
	private User user;
	private SubscriptionType subscriptionType;
	@Column (name = "ValidFrom")
	private LocalDate startDate;
	
	public Subscription () {
	
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
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
	
	public LocalDate getStartDate () {
		return startDate;
	}
	
	public void setStartDate (LocalDate startDate) {
		this.startDate = startDate;
	}
	
}
