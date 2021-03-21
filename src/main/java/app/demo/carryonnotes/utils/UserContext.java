package app.demo.carryonnotes.utils;

import app.demo.carryonnotes.pojo.LoggedInUser;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContext {
    private LoggedInUser user;

    public Long getLoggedInUserId() {
        return this.user.getId();
    }

    public String getLoggedInUserName() {
        return this.user.getUserName();
    }

    public void setLoggedInUser(String userName, Long id) {
        this.user = new LoggedInUser(userName, id);
    }
}
