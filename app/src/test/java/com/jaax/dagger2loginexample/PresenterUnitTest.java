package com.jaax.dagger2loginexample;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.jaax.dagger2loginexample.data.model.User;
import com.jaax.dagger2loginexample.login.LoginActivityMVP;
import com.jaax.dagger2loginexample.login.LoginActivityPresenter;
import com.jaax.dagger2loginexample.login.LoginRepository;
import com.jaax.dagger2loginexample.login.MemoryRepository;

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
}
