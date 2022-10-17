package com.jaax.dagger2loginexample.login;

import com.jaax.dagger2loginexample.data.model.User;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        User newUser = new User(0, firstName, lastName);
        repository.saveUser(newUser);
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }
}
