package th.ac.ku.kps.eng.cpe.datasecurity.security.service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import th.ac.ku.kps.eng.cpe.datasecurity.model.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String name;
	private String password;
	private Collection<SimpleGrantedAuthority> authorities;
	
	public UserDetailsImpl(int id, String username, String name, String password,
			Collection<SimpleGrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetails build(User user) {
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());	
		return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getName(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
