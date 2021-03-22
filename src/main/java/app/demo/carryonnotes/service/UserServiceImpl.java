package app.demo.carryonnotes.service;

import app.demo.carryonnotes.entity.User;
import app.demo.carryonnotes.error.EmailTakenException;
import app.demo.carryonnotes.pojo.UserDTO;
import app.demo.carryonnotes.pojo.UserVO;
import app.demo.carryonnotes.repository.UserRepository;
import app.demo.carryonnotes.utils.UserContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserContext userContext;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            UserContext userContext,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userContext = userContext;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserVO createNewUser(UserDTO user) throws EmailTakenException{
        if(emailUsed(user.getEmail())) {
            throw new EmailTakenException();
        }

        User newUser = new User();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encryptedPassword);
        User createdUser = this.userRepository.save(newUser);

        return new UserVO(createdUser.getId(), createdUser.getEmail());
    }

    @Override
    public UserVO logInUser(UserDTO user) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User loggedInUser = this.userRepository.findByEmail(user.getEmail());
        this.userContext.setLoggedInUser(loggedInUser.getEmail(), loggedInUser.getId());
        return new UserVO(loggedInUser.getId(), loggedInUser.getEmail());
    }

    @Override
    public void logOutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.logout(request, response, auth);
        }
    }

    private boolean emailUsed(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
