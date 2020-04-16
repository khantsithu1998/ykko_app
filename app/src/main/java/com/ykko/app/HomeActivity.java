package com.ykko.app;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.ramotion.circlemenu.CircleMenuView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_reservation,R.id.nav_branches,R.id.nav_food_menu,R.id.nav_feedback,
                R.id.nav_table_reservation,R.id.nav_confirm_order,R.id.nav_reserved)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        CircleMenuView circleMenuView = findViewById(R.id.ramotion_circle_menu);
        circleMenuView.setEventListener(new CircleMenuView.EventListener(){
            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView circleView, int index) {
                toMenu(index);
            }
        });
    }

    public void toMenu(int index){
        switch (index){
            case 0:
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.nav_home);
                break;
            case 1:
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.nav_reservation);
                break;
            case 2:
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.nav_branches);
                break;
            case 3:
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.nav_food_menu);
                break;
            case 4:
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.nav_feedback);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
