package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.firstapplicationcst2355.R;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //drawer layout to manage the nav drawer
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the drawer and navigation view from the layout
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //create action bar toggle for the nav drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //method for menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //method to create toasts when options are picked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_1) {
            showToast("You clicked on item 1");
            return true;
        } else if (id == R.id.menu_item_2) {
            showToast("You clicked on item 2");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    //method to navigate the pages
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            //if not already in the main activity, go to main
            if (!this.getClass().equals(MainActivity.class)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        } else if (id == R.id.nav_dad_joke) {
            //if not already in dad joke activity, go to dad joke activity
            if (!this.getClass().equals(DadJokeActivity.class)) {
                startActivity(new Intent(this, DadJokeActivity.class));
                finish();
            }
        } else if (id == R.id.nav_exit) {
            //exit the app
            finishAffinity();
        }
        //close the drawer after item selected
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
