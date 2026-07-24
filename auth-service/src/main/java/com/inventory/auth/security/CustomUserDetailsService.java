package com.inventory.auth.security;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.inventory.auth.repository.UserRepository;
@Service public class CustomUserDetailsService implements UserDetailsService { private final UserRepository userRepository; public CustomUserDetailsService(UserRepository userRepository){this.userRepository=userRepository;} public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { com.inventory.auth.entity.User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found with username: "+username)); return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()))); } }
