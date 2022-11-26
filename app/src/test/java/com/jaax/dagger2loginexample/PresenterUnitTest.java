package com.jaax.dagger2loginexample;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jaax.dagger2loginexample.data.model.User;
import com.jaax.dagger2loginexample.login.LoginActivityMVP;
import com.jaax.dagger2loginexample.login.LoginActivityPresenter;

import org.junit.Before;
import org.junit.Test;

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

        //when(model.getUser()).thenReturn(user);
        /*when(view.getFirstName()).thenReturn("jaax");
        when(view.getLastName()).thenReturn("dev");*/

        presenter = new LoginActivityPresenter(model);
        presenter.setView(view);
    }

    @Test
    public void noHayInteraccionConLaVista() {
        presenter.getCurrentUser();

        //verifyNoInteractions(view);

        /* verifica que el mockView llama una sola vez al método showUserNotAvailable
            cuando no hay un usuario creado */
        verify(view, times(1)).showUserNotAvailable();
    }

    @Test
    public void cargarUsuarioDelRepositorioCuandoElUsuarioActualExiste() {
        when(model.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //comprobamos la interacción con el model de datos
        verify(model, times(1)).getUser();

        //comprobamos la interacción con la vista
        verify(view, times(1)).setFirstName("jaax");
        verify(view, times(1)).setLastName("dev");
        verify(view, never()).showUserNotAvailable();
    }

    @Test
    public void showErrorMessageWhenUserIsNull() {
        when(model.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //comprobamos la interacción con el model de datos
        verify(model, times(1)).getUser();

        //comprobamos la interacción con la vista
        verify(view, never()).setFirstName("jaax");
        verify(view, never()).setLastName("dev");
        verify(view, times(1)).showUserNotAvailable();
    }


    public void createErrorMessageIfAnyIsEmpty() {
        //primera prueba poniendo firstName vacío
        when(view.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(view, times(1)).getFirstName();
        verify(view, never()).getLastName();
        verify(view, times(1)).showInputError();

        //segunda prueba con el campo lastname name vacío
        when(view.getFirstName()).thenReturn("jaax");
        when(view.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(view, times(2)).getFirstName();
        verify(view, times(1)).getLastName();
        verify(view, times(2)).showInputError();
    }

    @Test
    public void saveValidUser() {
        when(view.getFirstName()).thenReturn("jaax");
        when(view.getLastName()).thenReturn("dev");

        presenter.loginButtonClicked();

        verify(view, times(2)).getFirstName();
        verify(view, times(1)).getLastName();

        verify(model, times(1)).createUser("jaax", "dev");
        verify(view, times(1)).showUserSaved();
    }
}
