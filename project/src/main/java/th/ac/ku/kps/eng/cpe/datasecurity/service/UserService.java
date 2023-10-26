package th.ac.ku.kps.eng.cpe.datasecurity.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Role;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;
import th.ac.ku.kps.eng.cpe.datasecurity.repository.UserRepository;
import th.ac.ku.kps.eng.cpe.datasecurity.security.exception.AccountAlreadyExistException;
import th.ac.ku.kps.eng.cpe.datasecurity.security.service.UserDetailsImpl;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;
	
	public User save (User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll () {
		return (List<User>) userRepository.findAll();
	}
	
	public User findById (int userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	public void deleteById (int id) {
		userRepository.deleteById(id);
	}
	
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}
	
	public boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
	
	public boolean userNameExists (String userName) {
		return userRepository.findByUserName(userName) != null;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		    return UserDetailsImpl.build(user);
	}
	
	public User registerNewAccount (User user,String roleName) throws AccountAlreadyExistException{
		if (emailExists(user.getEmail())) {
            throw new AccountAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }else if(userNameExists(user.getUsername())) {
        	throw new AccountAlreadyExistException("There is an account with that userName : " + user.getUsername());
        }
		else {
			Role role = roleService.findByRoleName(roleName);
			user.getRoles().add(role);
			user = save(user);
			return user;
        }
	}
	
	public List<User> findAllByRole (int page, int value, String role) {
		Pageable pageable = PageRequest.of(page-1, value);
		return userRepository.findAllByRole(role, pageable);
	}
	
	public User findByUserName (String username) {
		return userRepository.findByUserName(username);
	}

}
