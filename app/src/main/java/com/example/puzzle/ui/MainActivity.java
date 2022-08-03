package com.example.puzzle.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.puzzle.R;

public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_signUp;
    private long backPressedTime;
    private Toast back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn_sign_in);
        btn_signUp = findViewById(R.id.btn_sign_up);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Login.class);
                startActivity(intent1);
            }
        });
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Signup.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            back.cancel();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            super.onBackPressed();
            return;
        } else {
            back = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_LONG);
            back.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}