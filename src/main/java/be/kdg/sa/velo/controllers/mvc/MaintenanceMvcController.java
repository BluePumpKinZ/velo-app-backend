package be.kdg.sa.velo.controllers.mvc;

import be.kdg.sa.velo.services.MaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceMvcController {

	private final MaintenanceService maintenanceService;
	
	public MaintenanceMvcController (MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@GetMapping
	public String maintenance(Model model) {
		model.addAttribute("vehicles", maintenanceService.getVehiclesInMaintenance());
		return "maintenance";
	}
	
}
