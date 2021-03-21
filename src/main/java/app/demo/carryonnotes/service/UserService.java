package app.demo.carryonnotes.service;

import app.demo.carryonnotes.error.EmailTakenException;
import app.demo.carryonnotes.pojo.UserDTO;
import app.demo.carryonnotes.pojo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    UserVO createNewUser(UserDTO user) throws EmailTakenException;

    UserVO logInUser(UserDTO user);

    void logOutUser(HttpServletRequest request, HttpServletResponse response);
}
