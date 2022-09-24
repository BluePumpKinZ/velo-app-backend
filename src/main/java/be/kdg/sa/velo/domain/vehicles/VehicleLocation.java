package be.kdg.sa.velo.domain.vehicles;

import java.time.LocalDateTime;

public record VehicleLocation(LocalDateTime timestamp, Vehicle vehicle, double latitude,double longitude) {}
