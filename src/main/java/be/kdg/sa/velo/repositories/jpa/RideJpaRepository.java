package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.rides.Ride;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RideJpaRepository extends JpaRepository<Ride, Integer> {
	
	@Query (value = "SELECT TOP(1) * FROM RIDES " +
			"WHERE ENDTIME IS NULL " +
			"AND VEHICLEID = ?1 " +
			"ORDER BY STARTTIME DESC", nativeQuery = true)
	Optional<Ride> getLastRideForVehicle (int vehicleId);
	
	@EntityGraph (attributePaths = {"vehicle"})
	@Query (value = """
			SELECT * FROM RIDES
			WHERE ENDTIME IS NULL
			AND STARTTIME < DATEADD (SECOND, -?1, GETDATE ())
			AND SUBSCRIPTIONID IS NOT NULL
			""", nativeQuery = true)
	List<Ride> getOpenRidesWithMinLength (int minRideDurationSeconds);
	
	
	String getNotMovingOpenRidesQuery = """
			SELECT * FROM RIDES R1
			WHERE R1.ENDTIME IS NULL
			  AND R1.SUBSCRIPTIONID IS NOT NULL
			  AND (SELECT COUNT(*) FROM VEHICLELOCATIONS VL1
			     WHERE VL1.VEHICLEID = R1.VEHICLEID
			       AND VL1.TIMESTAMP > R1.STARTTIME
			       AND GEOGRAPHY\\:\\:STGEOMFROMTEXT(VL1.LOCATION.MAKEVALID().STASTEXT(), 4326)
			         .STDISTANCE(GEOGRAPHY\\:\\:STGEOMFROMTEXT(
			             (SELECT TOP (1) VL2.LOCATION.MakeValid().STAsText()
			              FROM VEHICLELOCATIONS VL2
			              WHERE VL2.VEHICLEID = R1.VEHICLEID
			              ORDER BY TIMESTAMP DESC)
			         , 4326)) < ?2) = 0
			""";
	@Query (value = getNotMovingOpenRidesQuery, nativeQuery = true)
	List<Ride> getNotMovingOpenRides (int minNotMovingDurationSeconds, double maxNotMovingDistance);
}
