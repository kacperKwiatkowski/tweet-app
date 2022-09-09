package com.kacperKwiatkowski.tweetApp.security.auth;

import com.kacperKwiatkowski.tweetApp.model.UserEntity;
import com.kacperKwiatkowski.tweetApp.repository.UserRepository;
import com.kacperKwiatkowski.tweetApp.security.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try{
            UserEntity user = userRepository.findUserEntityByUsername(username);

            return org.springframework.security.core.userdetails
                    .User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(RoleType.valueOf(user.getRoleType()).getGrantedAuthorities())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
    }
}
