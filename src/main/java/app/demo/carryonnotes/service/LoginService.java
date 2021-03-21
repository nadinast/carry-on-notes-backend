package app.demo.carryonnotes.service;

import app.demo.carryonnotes.entity.User;
import app.demo.carryonnotes.pojo.UserDetailsImpl;
import app.demo.carryonnotes.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService{

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(email == null)
            throw new UsernameNotFoundException(email);
        return new UserDetailsImpl(user);
    }
}
