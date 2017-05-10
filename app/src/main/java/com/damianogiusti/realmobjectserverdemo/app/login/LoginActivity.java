package com.damianogiusti.realmobjectserverdemo.app.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.damianogiusti.realmobjectserverdemo.R;
import com.damianogiusti.realmobjectserverdemo.app.BaseActivity;
import com.damianogiusti.realmobjectserverdemo.app.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject Context context;

    @Inject LoginPresenter loginPresenter;

    @BindView(R.id.txtUsername) EditText txtUsername;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.btnLogin) Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews(this);

        loginPresenter.create(this);

        btnLogin.setOnClickListener(v -> loginPresenter.loginUser(txtUsername.getText(), txtPassword.getText()));
    }

    @Override
    protected void onInject() {
        getApplicationContext().getApplicationComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        loginPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.destroy();
    }

    @Override
    public void showLastUsedUsername(String username) {
        txtUsername.setText(username);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}