package com.ykko.app.ui.admin_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.ramotion.circlemenu.CircleMenuView;
import com.ykko.app.R;

public class AdminHomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Toolbar toolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.admin_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_admin_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_admin_home,R.id.nav_admin_reservation,
                R.id.nav_admin_food_menu,R.id.nav_admin_review,
                R.id.nav_admin_add_menu,R.id.nav_admin_edit_menu,R.id.nav_admin_reservation_detail,R.id.nav_admin_food_detail)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_admin_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        CircleMenuView circleMenuView = findViewById(R.id.admin_ramotion_circle_menu);
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
                Navigation.findNavController(this,R.id.nav_admin_host_fragment).navigate(R.id.nav_admin_home);
                break;
            case 1:
                Navigation.findNavController(this,R.id.nav_admin_host_fragment).navigate(R.id.nav_admin_reservation);
                break;
            case 2:
                Navigation.findNavController(this,R.id.nav_admin_host_fragment).navigate(R.id.nav_admin_food_menu);
                break;
            case 3:
                Navigation.findNavController(this,R.id.nav_admin_host_fragment).navigate(R.id.nav_admin_review);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_admin_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
