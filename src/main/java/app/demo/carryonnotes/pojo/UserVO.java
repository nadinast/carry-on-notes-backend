package app.demo.carryonnotes.pojo;

import app.demo.carryonnotes.entity.User;

public class UserVO {
    private Long id;
    private String email;

    public UserVO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public UserVO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
