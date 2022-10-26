package be.kdg.sa.velo.controllers.mvc;

import be.kdg.sa.velo.services.MaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/maintenance")
public class MaintenanceMvcController {

	private final MaintenanceService maintenanceService;
	
	public MaintenanceMvcController (MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}
	
	
	
}