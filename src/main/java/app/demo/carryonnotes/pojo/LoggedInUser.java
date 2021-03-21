package app.demo.carryonnotes.pojo;

public class LoggedInUser {

    private String userName;
    private Long id;

    public LoggedInUser(String userName, Long id) {
        this.userName = userName;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public Long getId() {
        return id;
    }
}
