package com.ykko.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ykko.app.data.model.AllPost;
import com.ykko.app.data.model.PopularPost;
import com.ykko.app.ui.admin_login.AdminLoginActivity;


public class HomeLoginActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = findViewById(R.id.visitor_button);
        Button adminBtn = findViewById(R.id.admin_button);


        final Intent intent = new Intent(this, HomeActivity.class);
        final Intent adminIntent = new Intent(this, AdminLoginActivity.class);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       startActivity(intent);
                                   }
                               }
        );

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(adminIntent);

            }
        });
    }
}
