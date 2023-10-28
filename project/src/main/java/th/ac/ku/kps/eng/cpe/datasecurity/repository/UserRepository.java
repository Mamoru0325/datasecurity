package th.ac.ku.kps.eng.cpe.datasecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.ac.ku.kps.eng.cpe.datasecurity.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("from User u where u.email = :email")
	public User findByEmail(@Param("email")String email);
	
	@Query("from User u where u.username = :username")
	public User findByUserName(@Param("username")String username);
	
	@Query("select u from User u inner join u.roles r where r.roleName = :role and  u.status = :status")
	public List<User> findAllByRole (@Param("role")String role, @Param("status")String status);

}
