package com.jaax.dagger2loginexample.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jaax.dagger2loginexample.data.di.App;
import com.jaax.dagger2loginexample.databinding.ActivityLoginBinding;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {
    private ActivityLoginBinding binding;

    @Inject
    public LoginActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((App) getApplication()).getComponent().inject(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return binding.edtName.getText().toString();
    }

    @Override
    public String getLastName() {
        return binding.edtLastname.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error: Usuario no disponible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Verifica tus credenciales", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        binding.edtName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        binding.edtLastname.setText(lastName);
    }
}