package com.penguinlabs.instagram;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FragmentContainerView fragmentContainerView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int position = menuItem.getItemId();

                if (position == R.id.home) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, HomeFragment.class, null)
                            .setReorderingAllowed(true)
                            .commit();
                } else if (position == R.id.reels) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, ReelFragment.class, null)
                            .setReorderingAllowed(true)
                            .commit();
                } else if (position == R.id.chat) {

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, ChatFragment.class, null)
                            .setReorderingAllowed(true)
                            .commit();
                } else if (position == R.id.search) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, SearchFragment.class, null)
                            .setReorderingAllowed(true)
                            .commit();
                } else if (position == R.id.profile) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, ProfileFragment.class, null)
                            .setReorderingAllowed(true)
                            .commit();
                }

                return true;
            }
        });

        getOnBackPressedDispatcher().addCallback(MainActivity.this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                int itemid = bottomNavigationView.getSelectedItemId();

                if (itemid != R.id.home) {
                    bottomNavigationView.setSelectedItemId(R.id.home);

                } else {
                    finish();
                }


            }
        });


    }
}