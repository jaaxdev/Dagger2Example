package com.jaax.dagger2loginexample.login;

import com.jaax.dagger2loginexample.data.model.User;

public interface LoginActivityMVP {
    interface Model {
        void createUser(String firstName, String lastName);
        User getUser();
    }

    interface Presenter {
        void setView(View view);
        void loginButtonClicked();
        void getCurrentUser();
    }

    interface View {
        String getFirstName();
        String getLastName();
        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();
        void setFirstName(String firstName);
        void setLastName(String lastName);
    }
}
