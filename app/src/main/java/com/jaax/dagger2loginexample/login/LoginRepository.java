package com.jaax.dagger2loginexample.login;

import com.jaax.dagger2loginexample.data.model.User;

public interface LoginRepository {
    void saveUser(User user);
    User getUser();
}
