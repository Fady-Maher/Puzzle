package com.example.puzzle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puzzle.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Login extends AppCompatActivity {
    TextInputLayout txt_email, txt_pass, email_address;
    CircularProgressButton btn_sign_In;
    String email, pass, emailAddress;
    TextView txt_forget;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    CircularProgressButton btn_google;

    //    private long backPressedTime;
//    private Toast back;
    Drawable d;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        setID();
        d = getResources().getDrawable(R.drawable.ic_baseline_done_24);

        sharedPreferences1 = getSharedPreferences("email_login", MODE_PRIVATE);
        editor = sharedPreferences1.edit();

        mAuth = FirebaseAuth.getInstance();
        btn_sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_sign_In.startAnimation();
                email = txt_email.getEditText().getText().toString().trim();
                pass = txt_pass.getEditText().getText().toString().trim();
                if (!isVaildEmail() | !isVaildPass()) {
                    btn_sign_In.revertAnimation();
                    return;
                } else {
                    signInWithEmailAndPassword();
                }

            }
        });
        txt_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Login.this);
                View view = LayoutInflater.from(Login.this).inflate(R.layout.forget_password_layout, null, false);
                CircularProgressButton reset = view.findViewById(R.id.btn_get_pass);
                email_address = view.findViewById(R.id.edit_email_forget);
                alertBuilder.setView(view);
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reset.startAnimation();

                        emailAddress = email_address.getEditText().getText().toString().trim();
                        if (!isVaildEmailForget()) {
                            reset.revertAnimation();
                            return;
                        } else {
                            mAuth.sendPasswordResetEmail(emailAddress)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                reset.doneLoadingAnimation(Color.parseColor("#126afc"), drawableToBitmap(d));
                                                Toast.makeText(getApplicationContext(), "Check your email for reset", Toast.LENGTH_LONG).show();
                                                alertDialog.dismiss();
                                            } else {
                                                reset.revertAnimation();
                                                Toast.makeText(getApplicationContext(), "There is error...!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }

                    }
                });
            }
        });
        createRequest();
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_google.startAnimation();
                signInwithgoogle();
            }
        });
    }

    private void setID() {
        txt_email = findViewById(R.id.edit_email);
        txt_pass = findViewById(R.id.edit_pass);
        btn_sign_In = findViewById(R.id.btn_login);
        txt_forget = findViewById(R.id.txt_forget);
        btn_google = findViewById(R.id.btn_google);
    }

    boolean isVaildEmail() {
        if (email.isEmpty()) {
            txt_email.getEditText().setError("Email can’t be empty..!");
            txt_email.getEditText().requestFocus();
            return false;
        }
        if (email.length() > 50) {
            txt_email.getEditText().setError("Email is too long..!");
            return false;
        }
        return true;
    }

    boolean isVaildEmailForget() {
        if (emailAddress.isEmpty()) {
            email_address.getEditText().setError("Email can’t be empty..!");
            email_address.getEditText().requestFocus();
            return false;
        }
        if (emailAddress.length() > 50) {
            email_address.getEditText().setError("Email is too long..!");
            return false;
        }
        return true;
    }

    boolean isVaildPass() {
        if (pass.isEmpty()) {
            txt_pass.getEditText().setError("Password can’t be empty..!");
            return false;
        }
        if (pass.length() < 6) {
            txt_pass.getEditText().setError("Password is too small..!");
            return false;
        }
        return true;
    }

    void signInWithEmailAndPassword() {
        mAuth.signInWithEmailAndPassword(email, pass).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mAuth.getCurrentUser().isEmailVerified()) {

                                sharedPreferences1 = getSharedPreferences("email_login", MODE_PRIVATE);
                                editor = sharedPreferences1.edit();
                                editor.putBoolean("login", true);
                                editor.apply();

                                txt_email.getEditText().setText("");
                                txt_pass.getEditText().setText("");
                                btn_sign_In.doneLoadingAnimation(Color.parseColor("#126afc"), drawableToBitmap(d));
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), " Please Verify Account First..! ", Toast.LENGTH_LONG).show();
                                btn_sign_In.revertAnimation();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                            btn_sign_In.revertAnimation();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                Log.e("error gmail", e.getMessage());
                Toast.makeText(getApplicationContext(), "Login failed." + e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_google.revertAnimation();
            }
        }

    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signInwithgoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sharedPreferences1 = getSharedPreferences("email_login", MODE_PRIVATE);
                            editor = sharedPreferences1.edit();
                            editor.putBoolean("login", true);
                            editor.apply();
                            btn_google.doneLoadingAnimation(Color.parseColor("#126afc"), drawableToBitmap(d));
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            btn_google.revertAnimation();
                        }
                    }
                });
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null && firebaseUser.isEmailVerified()) {
            Intent intent = new Intent(Login.this, Dashboard.class);
            startActivity(intent);
        }
    }

}