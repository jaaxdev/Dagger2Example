package com.jaax.dagger2loginexample.data.di;

import android.app.Application;

import com.jaax.dagger2loginexample.data.di.component.ApplicationComponent;
import com.jaax.dagger2loginexample.data.di.component.DaggerApplicationComponent;
import com.jaax.dagger2loginexample.data.di.module.ApplicationModule;
import com.jaax.dagger2loginexample.data.di.module.LoginModule;

public class App extends Application {

    private ApplicationComponent component;

    //aquí se inicializan lo que son los módulos y componentes de dagger
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}