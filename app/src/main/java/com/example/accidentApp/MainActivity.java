package com.example.accidentApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.accident_app.R;
import com.example.accident_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    HomeFragment homeFragment;
    AddLocationFragment addLocationFragment;
    StatusFragment statusFragment;
    HistoryFragment historyFragment;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment = new HomeFragment();
        addLocationFragment=new AddLocationFragment();
        statusFragment=new StatusFragment();
        historyFragment=new HistoryFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameContainer, homeFragment)
                .commit();


        bottomNavigationView=findViewById(R.id.bottomNavigationBar);
        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView= findViewById(R.id.sidebar);
        Toolbar toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            navigationView.setCheckedItem(R.id.home);

        }


        binding.bottomNavigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();

                if(id== R.id.home){
                   getSupportActionBar().setTitle("Dashboard");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer,homeFragment)
                            .commit();
                    return true;
                } else if (id==R.id.location) {
                    getSupportActionBar().setTitle("Add_Location");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer,addLocationFragment)
                            .commit();
                    return true;

                } else if (id==R.id.status){
                    getSupportActionBar().setTitle("Status");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer, statusFragment)
                            .commit();
                    return true;
                }
                else if(id==R.id.history) {
                    getSupportActionBar().setTitle("History");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer,historyFragment)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }
}