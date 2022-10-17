package com.jaax.dagger2loginexample.login;

import com.jaax.dagger2loginexample.data.model.User;
import com.jaax.dagger2loginexample.login.LoginRepository;

public class MemoryRepository implements LoginRepository {
    private User user;
    @Override
    public void saveUser(User user) {
        if( user == null ){
            user = getUser();
        }
        this.user = user;
    }

    @Override
    public User getUser() {
        if( user == null ){
            user = new User(0, "jaax", "dev" );
            user.setId(0);
        }
        return user;
    }
}
