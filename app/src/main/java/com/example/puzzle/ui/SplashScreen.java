package com.example.puzzle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.puzzle.fragment.OnBoardingFragment1;
import com.example.puzzle.fragment.OnBoardingFragment2;
import com.example.puzzle.fragment.OnBoardingFragment3;
import com.example.puzzle.R;


public class SplashScreen extends AppCompatActivity {
    ImageView splashImg;
    LottieAnimationView lottieAnimationView;
    TextView txt_animal, txt_finish1, txt_finish2, txt_finish;
    private static final int NUM_PAGES = 3;
    ViewPager viewPager;
    ScreenSlidePagerAdapter pagerAdapter;
    OnBoardingFragment1 tab1;
    OnBoardingFragment2 tab2;
    OnBoardingFragment3 tab3;
    boolean shareed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Permissions
        if (ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashScreen.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("email_login", MODE_PRIVATE);
        shareed = sharedPreferences.getBoolean("login", false);
        if (shareed == true) {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
        } else {

            txt_animal = findViewById(R.id.txt_animal_splash);
            splashImg = findViewById(R.id.img_splash);
            lottieAnimationView = findViewById(R.id.wolf);
            viewPager = findViewById(R.id.pager);

            pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);

            splashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
            txt_animal.animate().translationY(3000).setDuration(1000).setStartDelay(4000);
            lottieAnimationView.animate().translationY(1800).setDuration(1000).setStartDelay(4000);

        }

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:
                    tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}