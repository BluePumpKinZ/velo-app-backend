package be.kdg.sa.velo.repositories;

import be.kdg.sa.velo.domain.vehicles.UndockedVehicle;
import be.kdg.sa.velo.domain.vehicles.Vehicle;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jonas Leijzen
 * 24/09/2022
 */

@Repository
@Qualifier("JDBC")
public class VehicleJDBCRepository implements VehicleRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public VehicleJDBCRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<? extends Vehicle> readAllVehicles () {
		String sql = "SELECT * FROM vehicles";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance (UndockedVehicle.class));
	}
}
