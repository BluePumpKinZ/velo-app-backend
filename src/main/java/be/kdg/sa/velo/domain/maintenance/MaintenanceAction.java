package be.kdg.sa.velo.domain.maintenance;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity (name = "MaintenanceActions")
public class MaintenanceAction {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "MaintenanceActionId", columnDefinition = "SMALLINT")
	private int id;
	private String solution;
	@OneToOne
	@JoinColumn (name = "MaintenanceFlaggingId", columnDefinition = "SMALLINT")
	private MaintenanceFlagging flagging;
	private LocalDateTime timestamp;
	
	public MaintenanceAction () {
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getSolution () {
		return solution;
	}
	
	public void setSolution (String solution) {
		this.solution = solution;
	}
	
	public MaintenanceFlagging getFlagging () {
		return flagging;
	}
	
	public void setFlagging (MaintenanceFlagging flagging) {
		this.flagging = flagging;
	}
	
	public LocalDateTime getTimestamp () {
		return timestamp;
	}
	
	public void setTimestamp (LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
