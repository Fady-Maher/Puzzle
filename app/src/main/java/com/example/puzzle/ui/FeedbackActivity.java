package com.example.puzzle.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puzzle.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.hsalf.smileyrating.SmileyRating;

public class FeedbackActivity extends AppCompatActivity {
    SmileyRating smiley;
    TextInputLayout feed;
    Button send;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        smiley = (SmileyRating) findViewById(R.id.smile_rating);
        feed = findViewById(R.id.feed);
        send = findViewById(R.id.send_feedback);
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid() + "";
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    if (!feed.getEditText().getText().toString().equals("")) {
                        SmileyRating.Type type = smiley.getSelectedSmiley();
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "fadymaher2630@gmail.com"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "User Id: " + id + "\n \n" + "feedback rating = " + type + "\n\n\n" + "user feedback : \n" + feed.getEditText().getText().toString());
                        startActivity(emailIntent);
                        finish();
                    }
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "No application can perform this action", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
