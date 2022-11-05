package be.kdg.sa.velo.controllers.mvc;

import be.kdg.sa.velo.dto.maintenance.MaintenanceActionDTO;
import be.kdg.sa.velo.services.MaintenanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceMvcController {

	private final MaintenanceService maintenanceService;
	
	public MaintenanceMvcController (MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@PostMapping("/add")
	public String addMaintenanceAction (@ModelAttribute("maintenanceActionDTO") MaintenanceActionDTO maintenanceActionDTO, BindingResult result, Model model) {
		if (result.hasErrors ())
			return "maintenance";
		maintenanceService.removeVehicleFromMaintenance(maintenanceActionDTO);
		return "maintenance";
	}

	@GetMapping
	public String maintenance(Model model) {
		model.addAttribute("flags", maintenanceService.getMaintenanceFlaggings());
		model.addAttribute("maintenanceActionDTO", new MaintenanceActionDTO());
		return "maintenance";
	}
}
