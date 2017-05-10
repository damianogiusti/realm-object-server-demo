package com.damianogiusti.realmobjectserverdemo.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.damianogiusti.realmobjectserverdemo.R;
import com.damianogiusti.realmobjectserverdemo.app.BaseActivity;
import com.damianogiusti.realmobjectserverdemo.app.login.LoginActivity;
import com.damianogiusti.realmobjectserverdemo.app.mvp.model.RealmViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView, NewRealmDialog.Listener {

    @Inject MainPresenter mainPresenter;

    @BindView(R.id.btnAddRealm) Button btnAddRealm;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private MainItemsAdapter mainItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews(this);
        mainPresenter.create(this);

        btnAddRealm.setOnClickListener(v -> mainPresenter.createNewItem());
    }

    @Override
    protected void onInject() {
        getApplicationComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {

            }
            break;
            case R.id.logoutMenuItem: {
                mainPresenter.logoutUser();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.destroy();
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showNewItemAddScreen() {
        NewRealmDialog.newInstance().show(getSupportFragmentManager(), NewRealmDialog.TAG);
    }

    @Override
    public void showItemsList(List<RealmViewModel> realmViewModels) {
        if (mainItemsAdapter == null) {
            mainItemsAdapter = new MainItemsAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(mainItemsAdapter);
        }
        mainItemsAdapter.setDataset(realmViewModels);
        mainItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveRequested(String realmName) {
        mainPresenter.saveNewItem(realmName);
    }
}
