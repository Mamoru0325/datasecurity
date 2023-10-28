package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Refreshtoken;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;

@Repository
public interface RefreshtokenRepository extends CrudRepository<Refreshtoken, Integer> {
	Optional<Refreshtoken> findByToken(String token);

	@Modifying
	int deleteByUser(User user);
	
	
	Optional<Refreshtoken> findByUser(User user);
}
