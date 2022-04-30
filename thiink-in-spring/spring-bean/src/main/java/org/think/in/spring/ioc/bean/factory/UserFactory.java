package org.think.in.spring.ioc.bean.factory;

import org.think.in.spring.ioc.overview.domain.User;

public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
