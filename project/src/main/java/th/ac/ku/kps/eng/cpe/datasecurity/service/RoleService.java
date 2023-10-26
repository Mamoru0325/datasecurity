package th.ac.ku.kps.eng.cpe.datasecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Role;
import th.ac.ku.kps.eng.cpe.datasecurity.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public Role save (Role role) {
		return roleRepository.save(role);
	}
	
	public List<Role> findAll () {
		return (List<Role>) roleRepository.findAll();
	}
	
	public Role findById (int roleId) {
		return roleRepository.findById(roleId).orElse(null);
	}
	
	public void deleteById (int id) {
		roleRepository.deleteById(id);
	}
	
	public Role findByRoleName (String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
