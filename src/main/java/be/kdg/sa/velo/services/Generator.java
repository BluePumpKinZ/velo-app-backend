package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class Generator {
  private static long MAX_VEHICLE_ID = 20;
  private Random random = new Random();
  private double leftTopLatitude = 51.24750419598143;
  private double leftTopLongitude = 4.348152653973005;
  private double rightBottomLatitude = 51.190734161436936;
  private double rightBottomLongitude = 4.449776186432437;

  public VehicleLocation getRandomVehicleLocation() {
    return new VehicleLocation(LocalDateTime.now(),
                               random.nextLong() + 1,
                               getRandomCoordinateInRange(51.24750419598143,
                                                          51.190734161436936),
                               getRandomCoordinateInRange(
                                 4.348152653973005,
                                 4.449776186432437));
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
