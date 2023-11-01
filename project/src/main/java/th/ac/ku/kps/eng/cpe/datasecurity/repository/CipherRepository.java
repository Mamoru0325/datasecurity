package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Cipher;
import th.ac.ku.kps.eng.cpe.datasecurity.model.Type;

@Repository
public interface CipherRepository extends CrudRepository<Cipher, Integer> {
	
	@Query(nativeQuery = true, value="select * from cipher c where c.level = :level order by rand() limit 10")
	public List<Cipher> find10RandomQuestion (@Param("level")String level);
	
	@Query("from Cipher c where c.level = :level")
	public List<Cipher> findByLevel (@Param("level")String level);
	
	@Query("select c from Cipher c inner join c.type t where c.level = :level and t = :type")
	public List<Cipher> findByLevelAndType(@Param("level")String level, @Param("type")Type type);

}
