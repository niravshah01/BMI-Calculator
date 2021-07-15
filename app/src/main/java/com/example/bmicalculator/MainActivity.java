package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth fauth;
        fauth = FirebaseAuth.getInstance();

        if(fauth.getCurrentUser()==null){
            Intent intent = new Intent(MainActivity.this, LoginAct.class);
            startActivity(intent);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,new BmiFragment()).commit();

        bnv = findViewById(R.id.bottom_navigation);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.menu_home:
                        fragment = new BmiFragment();
                        break;
                    case R.id.menu_explore:
                        fragment = new WorkoutFragment();
                        break;
                    case R.id.menu_about:
                        fragment = new AboutFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,fragment).commit();

                return true;
            }
        });
    }
}