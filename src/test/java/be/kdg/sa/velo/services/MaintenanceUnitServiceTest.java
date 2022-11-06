package be.kdg.sa.velo.services;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.maintenance.MaintenanceAction;
import be.kdg.sa.velo.domain.maintenance.MaintenanceFlagging;
import be.kdg.sa.velo.domain.rides.Ride;
import be.kdg.sa.velo.domain.stations.Lock;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleTypeEnum;
import be.kdg.sa.velo.dto.maintenance.MaintenanceActionDTO;
import be.kdg.sa.velo.dto.vehicles.calls.LockDockedVehicleCall;
import be.kdg.sa.velo.dto.vehicles.calls.LockVehicleCall;
import be.kdg.sa.velo.exceptions.LockNotFoundException;
import be.kdg.sa.velo.exceptions.VehicleNotFoundException;
import be.kdg.sa.velo.maintenance.qualifiers.MaintenanceQualifyContext;
import be.kdg.sa.velo.repositories.*;
import be.kdg.sa.velo.utils.PointUtils;
import be.kdg.sa.velo.utils.VehicleTypeUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Point;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MaintenanceUnitServiceTest extends VeloApplicationTests {

    @Autowired
    private MaintenanceService maintenanceService;
    @MockBean
    private MaintenanceFlaggingRepository maintenanceFlaggingRepository;
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private LockRepository lockRepository;
    @MockBean
    private RideRepository rideRepository;
    @MockBean
    private MaintenanceActionRepository maintenanceActionRepository;

    @BeforeAll
    void setup() {
        //vehicle mocken
        var vehicle = new Vehicle ();
        vehicle.setId(1);
        vehicle.setLocation(PointUtils.createPoint(51.1, 3.9));
        vehicle.setLastMaintenanceDate(LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40));
        vehicle.setSerialNumber("123456789");
        given(vehicleRepository.findById(1)).willReturn(Optional.of (vehicle));

        //lock mocken
        var lock = new Lock();
        lock.setId(1);
        lock.setStationLockNr(1);
        lock.setVehicle(vehicle);
        given(lockRepository.getClosestLock(vehicle.getLocation ().getX (), vehicle.getLocation ().getY ())).willReturn(Optional.of(lock));
    }

    @Test
    void addVehicleToMaintenanceIfRequired() {
        maintenanceService.addVehicleToMaintenanceIfRequired (new MaintenanceQualifyContext() {
            @Override public int getVehicleId () {
                return 1;
            }
            @Override public LockVehicleCall getEvent () {
                return new LockDockedVehicleCall (1, 1, true, 1);
            }
            @Override public VehicleTypeEnum getVehicleTypeEnum () { return VehicleTypeEnum.VELO_BIKE; }
        });

        ArgumentCaptor<MaintenanceFlagging> flagArgumentCaptor = ArgumentCaptor.forClass (MaintenanceFlagging.class);
        verify (maintenanceFlaggingRepository, times (1)).save (flagArgumentCaptor.capture ());

        ArgumentCaptor<Ride> rideArgumentCaptor = ArgumentCaptor.forClass (Ride.class);
        verify (rideRepository, times (1)).save (rideArgumentCaptor.capture ());
        Ride ride = rideArgumentCaptor.getValue ();

        assertNotNull(ride);
        assertNull(ride.getSubscription());
        assertEquals(1, ride.getVehicle().getId());
        assertNull(ride.getEndLock());

        MaintenanceFlagging flag = flagArgumentCaptor.getValue ();
        assertEquals(1, flag.getVehicle().getId());

        //delete (add action)

        given(maintenanceFlaggingRepository.findById(flag.getId())).willReturn(Optional.of(flag));
        given(rideRepository.getLastRideForVehicle(flag.getVehicle().getId())).willReturn(Optional.of(ride));

        maintenanceService.removeVehicleFromMaintenance(new MaintenanceActionDTO(flag.getId(),"test"));

        ArgumentCaptor<Ride> rideArgumentCaptor1 = ArgumentCaptor.forClass (Ride.class);
        verify (rideRepository, times (2)).save (rideArgumentCaptor1.capture ());

        ArgumentCaptor<MaintenanceAction> actionArgumentCaptor = ArgumentCaptor.forClass (MaintenanceAction.class);
        verify (maintenanceActionRepository, times (1)).save (actionArgumentCaptor.capture ());
        MaintenanceAction action = actionArgumentCaptor.getValue ();

        assertEquals(flag.getId(), action.getFlagging().getId());
        assertEquals("test", action.getSolution());
    }
}