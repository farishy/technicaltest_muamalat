package com.example.technicaltest_muamalat.service;

import com.example.technicaltest_muamalat.model.User;
import com.example.technicaltest_muamalat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND!");
        } else {
//            List<GrantedAuthority> authorities = this.getAuthorities(user.getRoles());
            return buildUserForAuthentication(user);
        }
    }

//    private List<GrantedAuthority> getAuthorities(Set<Role> userRoles) {
//        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//        for(Role role : userRoles) {
//            roles.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        List <GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        return grantedAuthorities;
//    }

    private UserDetails buildUserForAuthentication(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getStatus() == 'A', true, true,
                true, getAuthorities2(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities2(String role_user) {
        return Collections.singleton(new SimpleGrantedAuthority(role_user));
    }
}
