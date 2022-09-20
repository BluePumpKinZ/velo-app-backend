package be.kdg.sa.velo.domain;

import java.time.LocalDateTime;

public record VehicleLocation(LocalDateTime timestamp,long vehicleId,double latitude,double longitude) {
}
