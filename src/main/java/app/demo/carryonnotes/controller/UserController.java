package app.demo.carryonnotes.controller;

import app.demo.carryonnotes.error.EmailTakenException;
import app.demo.carryonnotes.pojo.UserDTO;
import app.demo.carryonnotes.pojo.UserVO;
import app.demo.carryonnotes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserVO> createNewUser(@RequestBody UserDTO user) {
        try {
            UserVO createdUserVO = this.userService.createNewUser(user);
            return new ResponseEntity(createdUserVO, HttpStatus.OK);
        } catch(EmailTakenException e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserVO> logInUser(@RequestBody UserDTO user) {
        UserVO loggedInUserVO = this.userService.logInUser(user);
        return new ResponseEntity(loggedInUserVO, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logOutUser(HttpServletRequest request, HttpServletResponse response) {
        this.userService.logOutUser(request, response);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
