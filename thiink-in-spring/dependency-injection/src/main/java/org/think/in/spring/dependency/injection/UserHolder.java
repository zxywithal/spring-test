package org.think.in.spring.dependency.injection;

import org.think.in.spring.ioc.overview.domain.User;

import java.util.StringJoiner;

public class UserHolder {

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserHolder.class.getSimpleName() + "[", "]")
                .add("user=" + user)
                .toString();
    }
}
