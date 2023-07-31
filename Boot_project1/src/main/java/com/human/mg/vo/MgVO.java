package com.human.mg.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
1. 시큐리티에서 현재의 VO를 사용하려면 첫번쨰 다음과 같이 VO를 작성해야 한다.
VO에서 UserDetails 인터페이스를 구현한다.

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {}
위 메서드에서 권한을 만들어 리턴해 주도록한다.
여기서는 	"ROLE_ADMIN, ROLE_USER" 형태의 스트링을 리스트로 변환하여 리턴하도록 구현함
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MgVO implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idx;
	private String userid;
	private String password;
	private String name;
	private String phone;
	private int point; //좋아요 받은수로 등급을 나눌꺼임
	private Date regDate;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	private String lev;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    
	    // 'lev' 문자열을 각 역할로 나눕니다.
	    String[] roles = lev.split(",");

	    for (String role : roles) {
	        // 'role' 변수에 역할 정보가 저장되어 있다고 가정하고, "ROLE_" 접두사를 붙여서 authorities에 추가합니다.
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
	    }

	    return authorities;
	}

	
	@Override
	public String getUsername() {
		return userid;
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
