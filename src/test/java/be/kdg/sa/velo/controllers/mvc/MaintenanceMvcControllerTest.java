package be.kdg.sa.velo.controllers.mvc;

import be.kdg.sa.velo.controllers.VeloApplicationMvcTests;
import be.kdg.sa.velo.domain.maintenance.MaintenanceFlagging;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.models.maintenance.MaintenanceFlag;
import be.kdg.sa.velo.repositories.MaintenanceFlaggingRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MaintenanceMvcControllerTest extends VeloApplicationMvcTests {

    @MockBean
    MaintenanceFlaggingRepository maintenanceFlaggingRepository;
    @Autowired
    private MockMvc mvc;

    @BeforeAll
    void setUp() {
        var obj1 = new MaintenanceFlag(1,"123","Step","Platte band");
        var obj2 = new MaintenanceFlag(2,"456","Fiets","Remmen stuk");
        given(maintenanceFlaggingRepository.getMaintenanceFlaggings()).willReturn(List.of(obj1, obj2));
    }

    @Test
    void shouldReturnMaintenancePageWithMaintenanceVehicles() throws Exception {
         this.mvc.perform(get("/maintenance"))
                 .andExpect(status().isOk())
                 .andExpect(view().name("maintenance"))
                 .andExpect(model().attributeExists("flags"))
                 .andExpect(model().attribute("flags", hasItem(allOf(
                         hasProperty("serialNumber", equalTo("123"))
                 ))))
                 .andExpect(model().attribute("flags", hasItem(allOf(
                         hasProperty("serialNumber", equalTo("456"))
                 ))))
                 .andExpect(model().attribute("flags", hasSize(2)));
    }

}