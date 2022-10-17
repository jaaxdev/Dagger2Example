package com.jaax.dagger2loginexample.data.di.component;

//Interfaz que servirá como referencia entre actividades, fragments o servicios
//Cada componente debe depender de un módulo

//indica que este componente depende los módulos debajo

import com.jaax.dagger2loginexample.data.di.module.LoginModule;
import com.jaax.dagger2loginexample.login.LoginActivity;
import com.jaax.dagger2loginexample.data.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    /*
     * las actividades, servicios o fragmentos que se deseen agregar como componentes
     * se deben declarar dentro de esta interfaz como métodos de inyección
     * */

    void inject(LoginActivity target);
}