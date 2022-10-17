package com.jaax.dagger2loginexample.login;

import androidx.annotation.Nullable;

import com.jaax.dagger2loginexample.data.model.User;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @Nullable
    private LoginActivityMVP.Model model;
    private LoginActivityMVP.View view;

    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if(view != null) {
            if(view.getFirstName().trim().equals("")
                && view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(
                        view.getFirstName(),
                        view.getLastName()
                );
                view.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();
        if(user == null) {
            if(view != null) {
                view.showUserNotAvailable();
            }
        } else {
            if (view != null) {
                view.setFirstName(user.getName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
