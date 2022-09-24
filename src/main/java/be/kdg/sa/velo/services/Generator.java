package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.Vehicle;
import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class Generator {
  private static long MAX_VEHICLE_ID = 20;
  private final Random random = new Random();
  private final double leftTopLatitude = 51.24750419598143;
  private final double leftTopLongitude = 4.348152653973005;
  private final double rightBottomLatitude = 51.190734161436936;
  private final double rightBottomLongitude = 4.449776186432437;

  public VehicleLocation getRandomVehicleLocation(Vehicle vehicle) {
    return new VehicleLocation (LocalDateTime.now (),
            vehicle,
            getRandomCoordinateInRange (leftTopLatitude,
                    rightBottomLatitude),
            getRandomCoordinateInRange (
                    leftTopLongitude,
                    rightBottomLongitude));
  }

  private double getRandomCoordinateInRange(double leftTop, double rightBottom) {
    if(leftTop > rightBottom){
      double temp = leftTop;
      leftTop=rightBottom;
      rightBottom=temp;
    }
    return random.nextDouble();
  }
}
