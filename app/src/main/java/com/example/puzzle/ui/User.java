package com.example.puzzle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.puzzle.classes.ID;
import com.example.puzzle.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class User extends AppCompatActivity {
    Toolbar toolbar;
    FirebaseUser user;
    FirebaseAuth mAuth;
    TextInputLayout userName, email;
    CircleImageView circleImageView;
    CircularProgressButton circularProgressButton;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount acct = null;
    DatabaseReference mDatabase;
    String id;
    int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0;
    TextView txt_1, txt_2, txt_3, txt_4, txt_5;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        setId();
        getUserData();
        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeleteAccount();
            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference("Animals").child(mAuth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ID n = dataSnapshot.getValue(ID.class);
                    //Log.e("ttttt",n.getId());
                    if (n.getId().equals("1")) {
                        c1++;
                    } else if (n.getId().equals("2")) {
                        c2++;
                    } else if (n.getId().equals("3")) {
                        c3++;
                    } else if (n.getId().equals("4")) {
                        c4++;
                    } else if (n.getId().equals("5")) {
                        c5++;
                    }
                }
                Log.e("tag ", c1 + "  " + c2 + "  " + c3 + "  " + c4 + "  " + c5);
                txt_1.setText(c1 + "");
                txt_2.setText(c2 + "");
                txt_3.setText(c3 + "");
                txt_4.setText(c4 + "");
                txt_5.setText(c5 + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setToolbar();

    }

    private void setToolbar() {
        toolbar = findViewById(R.id.tool_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("");
    }

    private void setId() {
        email = findViewById(R.id.email_view);
        userName = findViewById(R.id.username_view);
        circleImageView = findViewById(R.id.user_image_view);
        circularProgressButton = findViewById(R.id.btn_delete_user);
        txt_1 = findViewById(R.id.txt_1);
        txt_2 = findViewById(R.id.txt_2);
        txt_3 = findViewById(R.id.txt_3);
        txt_4 = findViewById(R.id.txt_4);
        txt_5 = findViewById(R.id.txt_5);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUserData() {
        if (user.getDisplayName() == null) {
            userName.getEditText().setText("None");
        } else {
            userName.getEditText().setText(user.getDisplayName());
        }
        if (user.getPhotoUrl() == null) {
            circleImageView.setImageResource(R.drawable.user_defult);
        } else {
            Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(circleImageView);
        }
        email.getEditText().setText(user.getEmail());
    }

    private void setDeleteAccount() {
        circularProgressButton.startAnimation();
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "AccountDeleted", Toast.LENGTH_SHORT).show();

                    sharedPreferences1 = getSharedPreferences("email_login", MODE_PRIVATE);
                    editor = sharedPreferences1.edit();
                    editor.putBoolean("login", false);
                    editor.apply();

                    if (acct != null) {
                        mGoogleSignInClient.signOut()
                                .addOnCompleteListener(User.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    } else {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        // LoginManager.getInstance().logOut();
                    }
                }
                circularProgressButton.revertAnimation();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}