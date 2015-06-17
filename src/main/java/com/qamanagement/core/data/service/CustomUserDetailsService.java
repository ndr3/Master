package com.qamanagement.core.data.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qamanagement.core.data.dao.UserDao;

@Service("customUserDetailsService")
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService, Serializable {
	
	private static final long serialVersionUID = 2872720005099155469L;
	
	@Autowired
    private UserDao userDao;   

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    	com.qamanagement.core.data.model.User domainUser = userDao.getUser(login);
    	if(domainUser==null){
    		throw new UsernameNotFoundException("");
    	}
       
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        

        return new User(
            domainUser.getEmail(),
            domainUser.getPassword(),
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            getAuthorities(domainUser.getRole().getId())
        );
    }
   
    public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
   
    public List<String> getRoles(Long role) {

        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ROLE_PROJECT_MANAGER");
        } else if (role.intValue() == 2) {
            roles.add("ROLE_EMPLOYEE");
        }
        return roles;
    }
   
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
