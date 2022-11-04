package be.kdg.sa.velo.repositories.jpa;

import be.kdg.sa.velo.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
	
	@Query (value = """
			SELECT DISTINCT S.USERID FROM SUBSCRIPTIONS S
			WHERE CURRENT_TIMESTAMP BETWEEN S.VALIDFROM AND (
			    CASE
			        WHEN S.SUBSCRIPTIONTYPEID = 1 THEN DATEADD (DAY, 1, S.VALIDFROM)
			        WHEN S.SUBSCRIPTIONTYPEID = 2 THEN DATEADD (MONTH, 1, S.VALIDFROM)
			        WHEN S.SUBSCRIPTIONTYPEID = 3 THEN DATEADD (YEAR, 1, S.VALIDFROM)
			    END)
			ORDER BY S.USERID
			""", nativeQuery = true)
	List<Integer> getValidSimulatorIds ();
	
}
