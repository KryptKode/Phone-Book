package com.kryptkode.cyberman.phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.kryptkode.cyberman.phonebook.fragments.DetailFragment;
import com.kryptkode.cyberman.phonebook.fragments.ModifyAddFragment;
import com.kryptkode.cyberman.phonebook.fragments.PhoneBookFragment;

public class MainActivity extends AppCompatActivity implements
        PhoneBookFragment.PhoneBookFragmentListener, ModifyAddFragment.ModifyAddFragmentListener {

    PhoneBookFragment phoneBookFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.fragmentContainer) != null){
            phoneBookFragment = new PhoneBookFragment();
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, phoneBookFragment);
            transaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                //TODO: Add Setting Menu
                Snackbar.make(findViewById(R.id.root_layout), "Setting", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                //TODO; Add About Menu
                Snackbar.make(findViewById(R.id.root_layout), "About", Snackbar.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addContact() {
        ModifyAddFragment modifyAddFragment = new ModifyAddFragment();

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, modifyAddFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void saveContact() {
        DetailFragment detailFragment = new DetailFragment();

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
