package th.ac.ku.kps.eng.cpe.datasecurity.model;
// Generated Oct 16, 2023, 11:42:57 AM by Hibernate Tools 5.6.3.Final

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private Integer roleId;
	private String roleName;
	@JsonIgnore
	private List<User> users = new ArrayList<User>();

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleName, List<User> users) {

		this.roleName = roleName;
		this.users = users;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	

}
