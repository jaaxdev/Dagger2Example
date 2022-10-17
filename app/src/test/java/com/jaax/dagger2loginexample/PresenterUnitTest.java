package com.jaax.dagger2loginexample;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.jaax.dagger2loginexample.data.model.User;
import com.jaax.dagger2loginexample.login.LoginActivityMVP;
import com.jaax.dagger2loginexample.login.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class PresenterUnitTest {

    LoginActivityMVP.Model model;
    LoginActivityMVP.Presenter presenter;
    LoginActivityMVP.View view;
    User user;

    @Before
    public void initialization() {
        model = mock(LoginActivityMVP.Model.class);
        view = mock(LoginActivityMVP.View.class);

        user = new User(0, "jaax", "dev");

        when(model.getUser()).thenReturn(user);
        when(view.getFirstName()).thenReturn("jaax");
        when(view.getLastName()).thenReturn("dev");

        presenter = new LoginActivityPresenter(model);
        presenter.setView(view);
    }

    @Test
    public void noHayInteraccionConLaVista() {
        presenter.getCurrentUser();
        verifyNoInteractions(view);
    }
}
