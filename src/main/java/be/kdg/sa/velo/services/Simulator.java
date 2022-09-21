package be.kdg.sa.velo.services;

import be.kdg.sa.velo.domain.vehicles.VehicleLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Simulator {
  private static final Logger logger = LoggerFactory.getLogger(Simulator.class);

  Generator generator;
  Messenger<VehicleLocation> msg;

  public Simulator(Generator generator, @Qualifier("XML") Messenger<VehicleLocation> msg) {
    this.generator = generator;
    this.msg = msg;
  }

  public void sendVehicleMessage(int nr){
    for (int i = 0; i <nr; i++) {
           msg.sendMessage(generator.getRandomVehicleLocation());
    }
  }
}
