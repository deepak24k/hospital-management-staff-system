package com.deepak.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "users")
@Data
@Getter 
@Setter 
@NoArgsConstructor 
@ToString
@JsonInclude(value = Include.NON_NULL)
public class User implements UserDetails {

private static final long serialVersionUID = -7924802436921425267L;


//	+---------+------------+------------+-------------------+----------+------------+------------+
//	| userId  | first_name | last_name  | email             | password | role       | phoneNumber|
//	+---------+------------+------------+-------------------+----------+------------+------------+
//	|   1     | deepak     | kumar      | deepak@gmail.com  | password | admin      | 9876543210 |
//	+---------+------------+------------+-------------------+----------+------------+------------+


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int userId;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private String phoneNumber;
	
	
	@Exclude
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Employee employee;
	
	//connection to employee
	public void addEmployee(Employee e) {
		this.employee=e;
		this.employee.setUser(this);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.getRole().toUpperCase()));

		return grantedAuthorities;
		
	}
	@Override
	public String getUsername() {
		return email;
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
	
}
