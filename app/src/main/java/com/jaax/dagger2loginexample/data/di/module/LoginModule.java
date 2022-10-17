package com.jaax.dagger2loginexample.data.di.module;

import com.jaax.dagger2loginexample.login.MemoryRepository;
import com.jaax.dagger2loginexample.login.LoginActivityMVP;
import com.jaax.dagger2loginexample.login.LoginActivityModel;
import com.jaax.dagger2loginexample.login.LoginActivityPresenter;
import com.jaax.dagger2loginexample.login.LoginRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository) {
        return new LoginActivityModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository() {
        return new MemoryRepository();
    }
}
